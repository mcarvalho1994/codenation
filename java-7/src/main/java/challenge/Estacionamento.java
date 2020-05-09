package challenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Estacionamento {

    private final List<Carro> carrosEstacionadosList = new ArrayList<>();

    public void estacionar(Carro carro) {
      if(carrosEstacionados() < 10)
      {
          verificarMotorista(carro);
          carrosEstacionadosList.add(carro);
      }
      else {
          verificarMotorista(carro);
          int carroListIndex = 0;
          while (carroListIndex < carrosEstacionadosList.size() && carrosEstacionadosList.get(carroListIndex).getMotorista().getIdade() > 55) {
              carroListIndex++;
          }
          if(carroListIndex < 10) {
              carrosEstacionadosList.remove(carroListIndex);
              carrosEstacionadosList.add(carro);

          }
          else {
              throw new EstacionamentoException("Estacionamento lotado!");
          }
      }
    }

    public int carrosEstacionados() {
        return carrosEstacionadosList.size();
    }

    public boolean carroEstacionado(Carro carro) {
        for(Carro c : carrosEstacionadosList)
        {
            if(c == carro)
                return true;
        }
        return false;
        //return carrosEstacionadosList.stream().anyMatch(c -> c.getPlaca().equals(carro.getPlaca()));

    }

    private void verificarMotorista(Carro carro)
    {
        if(carro.getMotorista() == null){
            throw new EstacionamentoException("Não são permitidos carros sem motorista!");
        }
        else if(carro.getMotorista().getIdade() < 18) {
            throw new EstacionamentoException("Motorista menor de idade!");
        }
        else if(carro.getMotorista().getPontos() > 20) {
            throw new EstacionamentoException("CNH suspensa ou inexistente!");
        }
    }
}
