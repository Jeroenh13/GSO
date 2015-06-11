package bank.bankieren;

import java.io.Serializable;

public interface IRekening extends Serializable {
  int getNr();
  Money getSaldo();
  void setSaldo(Money newSaldo);
  IKlant getEigenaar();
  int getKredietLimietInCenten();
}

