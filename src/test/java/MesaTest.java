import com.amandalima.minicurso.Cliente;
import com.amandalima.minicurso.Mesa;
import com.amandalima.minicurso.Pedido;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MesaTest {
    @Test
    public void deveCriarMesaCorretamente(){
        int numero = 1;
        int cadeiras = 1;

        Mesa mesa = new Mesa(numero, cadeiras);

        assertThat(mesa.getCadeiras()).isEqualTo(1);
    }

    @Test
    public void naoDeveCriarMesaComZeroCadeiras() {
        int numero = 2;
        int cadeiras = 0;

        Mesa mesa = new Mesa(numero, cadeiras);

        assertThat(mesa).isNotEqualTo(new Mesa(2, 0));
    }

    @Test
    public void naoDeveAdicionarPedidosEmUmaMesaVazia(){
        Mesa mesa = new Mesa(3, 2);

        assertThat(mesa.getCadeiras()).isEqualTo(2);

        Pedido pedido = new Pedido("macarrao", "coca-cola", 50);

        mesa.addPedido(pedido);

        assertThat(mesa.getPedidos()).isEmpty();
    }

    @Test
    public void deveAdicionarPedidoEmUmaMesaComClientes() {
        Mesa mesa = new Mesa(3, 2);
        mesa.addCliente(new Cliente("Julius"));

        Pedido pedido = new Pedido("macarrao", "coca-cola", 5);
        mesa.addPedido(pedido);

        assertThat(mesa.getPedidos().size()).isEqualTo(1);
    }

    @Test
    public void deveMostrarOcupacaoDaMesaCorretamente() {
        Mesa mesa = new Mesa(3, 2);
        mesa.addCliente(new Cliente("Julius"));

        Map<Integer, String> resultado = mesa.getOcupacao();

        assertThat(resultado.get(1)).isEqualTo("Julius");
        assertThat(resultado.get(2)).isEqualToIgnoringCase("Vazia");

    }
}
