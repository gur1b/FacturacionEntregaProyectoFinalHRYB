package main.java.edu.coderhouse.jpa;

import main.java.edu.coderhouse.jpa.model.Cliente;
import main.java.edu.coderhouse.jpa.model.DetalleFactura;
import main.java.edu.coderhouse.jpa.model.Factura;
import main.java.edu.coderhouse.jpa.model.Producto;
import main.java.edu.coderhouse.jpa.services.ClienteService;
import main.java.edu.coderhouse.jpa.services.DetalleFacturaService;
import main.java.edu.coderhouse.jpa.services.FacturaService;
import main.java.edu.coderhouse.jpa.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OneApplication implements CommandLineRunner {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private FacturaService facturaService;
	@Autowired
	private DetalleFacturaService detalleFacturaService;

	public OneApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(OneApplication.class, args);
		System.out.println("Inicialización exitosa");
	}
//ESPECIE DE TEST PARA VER SI FUNCIONA CORRECTAMENTE AGREGAR ENTIDADES A LA DB
	public void run(String... args) throws Exception {
		try {
			System.out.println("****PRUEBA DE LA DB****");

			List<Cliente> clientes = clienteService.traerTodos();
			System.out.println("*Número de clientes incial: " + clientes.size());

			List<Producto> productos = productoService.traerTodos();
			System.out.println("*Número de productos incial: " + productos.size());

			List<Factura> facturas = facturaService.traerTodos();
			System.out.println("*Número de facturas incial: " + facturas.size());


			System.out.println("Creando nuevas entidades...");
			Cliente clientePrueba = new Cliente("Cliente", "Prueba", "23042004");
			clienteService.crear(clientePrueba);
			Producto productoPrueba = new Producto("Laptop Gamer", "P006", 10, 1500.0);
			this.productoService.crear(productoPrueba);
			Producto productoPrueba2 = new Producto("Laptop", "P007", 10, 1500.0);
			this.productoService.crear(productoPrueba2);

			Factura factura = new Factura(clientePrueba, 1500.0);
			this.facturaService.crear(factura);
			DetalleFactura detalleFactura1 = new DetalleFactura( factura,2, productoPrueba, 1500.0);
			this.detalleFacturaService.crear(detalleFactura1);
			DetalleFactura detalleFactura2 = new DetalleFactura( factura,3, productoPrueba2, 1500.0);
			this.detalleFacturaService.crear(detalleFactura2);

			factura.setDetalles(List.of(detalleFactura1,detalleFactura2));


			List<Cliente> clientesAfter = clienteService.traerTodos();
			System.out.println("*Número de cliente finals: " + clientesAfter.size());


			List<Producto> productosAfter = productoService.traerTodos();
			System.out.println("*Número de productos final: " + productosAfter.size());


			List<Factura> facturasAfter = facturaService.traerTodos();
			System.out.println("*Número de facturas final: " + facturasAfter.size());



		} catch (Exception var15) {
			var15.printStackTrace(System.out);
		}

	}
}