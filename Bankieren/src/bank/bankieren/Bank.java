package bank.bankieren;

import bank.internettoegang.IBalie;
import centrale.server.BankNotFoundException;
import centrale.server.IBankCentrale;
import fontys.util.*;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank extends UnicastRemoteObject implements IBank {

    private static final long serialVersionUID = -8728841131739353765L;
    private Map<Integer, IRekeningTbvBank> accounts;
    private Map<String, Integer> bankNumbers;
    private Collection<IKlant> clients;
    private int nieuwReknr;
    private String name;

    public Bank(String name) throws RemoteException {
        accounts = new HashMap<Integer, IRekeningTbvBank>();
        bankNumbers = new HashMap<String, Integer>();
        clients = new ArrayList<IKlant>();

        bankNumbers.put("RaboBank", 100000000);
        bankNumbers.put("ING", 200000000);
        bankNumbers.put("SNS", 300000000);
        bankNumbers.put("ABN+AMRO", 400000000);
        bankNumbers.put("ASN", 500000000);

        System.out.println(name);
        nieuwReknr = bankNumbers.get(name);
        this.name = name;
    }

    public synchronized int openRekening(String name, String city) {
        if (name.equals("") || city.equals("")) {
            return -1;
        }

        IKlant klant = getKlant(name, city);
        IRekeningTbvBank account = new Rekening(nieuwReknr, klant, Money.EURO);
        accounts.put(nieuwReknr, account);
        nieuwReknr++;
        return nieuwReknr - 1;
    }

    private IKlant getKlant(String name, String city) {
        for (IKlant k : clients) {
            if (k.getNaam().equals(name) && k.getPlaats().equals(city)) {
                return k;
            }
        }
        IKlant klant = new Klant(name, city);
        clients.add(klant);
        return klant;
    }

    public IRekening getRekening(int nr) {
        return accounts.get(nr);
    }

    @Override
    public boolean maakOver(int source, int destination, Money money)
            throws NumberDoesntExistException, RemoteException, BankNotFoundException {
        
        String sourceBankName = getBankName(source);
        String targetBankName = getBankName(destination);
        
        IBalie balie = null;
        IBank destinationBank = null;
        
        if(!sourceBankName.equalsIgnoreCase(targetBankName))
        {
            System.out.println("Different banks contacting central server");
            try {
                String address = java.net.InetAddress.getLocalHost().getHostAddress();
                int port = 1099;
                String rmiCentrale = address + ":" + port + "/" + "Centrale";
                IBankCentrale centrale = (IBankCentrale) Naming.lookup("rmi://" + rmiCentrale);
                balie = centrale.getBank(targetBankName);
                destinationBank = balie.getBank();
                System.out.println("Bank found: " + destinationBank.getName());
            } catch (UnknownHostException | NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else
        {
            try {
                String address = java.net.InetAddress.getLocalHost().getHostAddress();
                int port = 1099;
                String rmiCentrale = address + ":" + port + "/" + "Centrale";
                IBankCentrale centrale = (IBankCentrale) Naming.lookup("rmi://" + rmiCentrale);
                balie = centrale.getBank(sourceBankName);
                destinationBank = this;
            } catch (UnknownHostException | NotBoundException | MalformedURLException | RemoteException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (source == destination) {
            throw new RuntimeException(
                    "cannot transfer money to your own account");
        }
        if (!money.isPositive()) {
            throw new RuntimeException("money must be positive");
        }

        IRekeningTbvBank source_account = (IRekeningTbvBank) getRekening(source);
        if (source_account == null) {
            throw new NumberDoesntExistException("account " + source
                    + " unknown at " + name);
        }

        Money negative = Money.difference(new Money(0, money.getCurrency()),
                money);
        boolean success = source_account.muteer(negative);
        if (!success) {
            return false;
        }
        
        if(destinationBank == null)
        {
            throw new BankNotFoundException(targetBankName + " did not register with central server");
        }
        IRekeningTbvBank dest_account = (IRekeningTbvBank) destinationBank.getRekening(destination);
        if (dest_account == null) {
            throw new NumberDoesntExistException("account " + destination
                    + " unknown at " + name);
        }
        success = dest_account.muteer(money);
        if(balie != null && success)
        {
            System.out.println("Informing: " + String.valueOf(destination));
            balie.inform(destination, (IRekening)dest_account);
        }

        if (!success) // rollback
        {
            source_account.muteer(money);
        }
        return success;
    }

    @Override
    public String getName() {
        return name;
    }
    
    private String getBankName(int num){
        String id = String.copyValueOf(String.valueOf(num).toCharArray(), 0, 1);
        
        for (Map.Entry<String, Integer> bank : bankNumbers.entrySet()) {
            
            if(id.equals(String.copyValueOf(String.valueOf(bank.getValue()).toCharArray(), 0, 1)))
                return bank.getKey();
            
        }
        return null;
    }

}
