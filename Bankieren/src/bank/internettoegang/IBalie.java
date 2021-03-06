package bank.internettoegang;

import bank.bankieren.IBank;
import bank.bankieren.IRekening;
import fontys.observer.RemotePropertyListener;
import fontys.observer.RemotePublisher;
import java.rmi.*;


public interface IBalie extends Remote , RemotePublisher {
  /**
   * creatie van een nieuwe bankrekening; het gegenereerde bankrekeningnummer is
   * identificerend voor de nieuwe bankrekening en heeft een saldo van 0 euro
   * @param naam van de eigenaar van de nieuwe bankrekening
   * @param plaats de woonplaats van de eigenaar van de nieuwe bankrekening
   * @param wachtwoord van het account waarmee er toegang kan worden verkregen 
   * tot de nieuwe bankrekening
   * @return null zodra naam of plaats een lege string of wachtwoord minder dan 
   * vier of meer dan acht karakters lang is en anders de gegenereerde 
   * accountnaam(8 karakters lang) waarmee er toegang tot de nieuwe bankrekening
   * kan worden verkregen
     * @throws java.rmi.RemoteException
   */
  String openRekening(String naam, String plaats, String wachtwoord) throws RemoteException;

  /**
   * er wordt een sessie opgestart voor het login-account met de naam
   * accountnaam mits het wachtwoord correct is
   * @param accountnaam
   * @param wachtwoord
   * @return de gegenereerde sessie waarbinnen de gebruiker 
   * toegang krijgt tot de bankrekening die hoort bij het betreffende login-
   * account mits accountnaam en wachtwoord matchen, anders null
     * @throws java.rmi.RemoteException
   */
  IBankiersessie logIn(String accountnaam, String wachtwoord) throws RemoteException;
  
  IBank getBank() throws RemoteException;

  /**
   * Triggered de balie om de de gebruiker van het rekeningnummer te informeren over de
   * gemaakt transactie
   * @param to 
     * @param rek 
     * @throws java.rmi.RemoteException 
   */
  public void inform(int to, IRekening rek) throws RemoteException;
  
  @Override
  public void addListener(RemotePropertyListener listener, String property) throws RemoteException;
  
  @Override
  public void removeListener(RemotePropertyListener listener, String property) throws RemoteException;

}

