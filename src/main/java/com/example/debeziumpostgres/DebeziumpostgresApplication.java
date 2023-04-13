package com.example.debeziumpostgres;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DebeziumpostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebeziumpostgresApplication.class, args);
	}
	
	@Component
	class DebeziumRoute extends RouteBuilder {
		
		private String offsetStorageFileName = "C:\\study\\debeziumpostgres\\offset-file.dat";
		private String host = "localhost";
		private String username = "postgres";
		private String password = "12345";
		private String db = "postgres";
		
		@Override
		public void configure() throws Exception {
			from("debezium-postgres:dbz-postgres?offsetStorageFileName=" + offsetStorageFileName
					+ "&databaseHostName=" + host
					+ "&databaseUser=" + username
					+ "&databasePassword=" + password
					+ "&databaseDbname=" + db
					+ "&pluginName=pgoutput") //decoderbufs
			.log("EVENTO: ${body}")
			.log(" identificador: ${headers.CamelDebeziumIdentifier}")
			.log(" source metadata: ${headers.CamelDebeziumSourceMetadata}")
			.log(" operacao: ${headers.CamelDebeziumOperation}")//tipo do evento, c = create (or insert), u = update, d = delete, r = snapshot
			.log(" base: '${headers.CamelDebeziumSourceMetadata[db]}'")
			.log(" tabela: '${headers.CamelDebeziumSourceMetadata[table]}'")
			.log(" chave primaria: ${headers.CamelDebeziumKey}");
		}
	}

}
