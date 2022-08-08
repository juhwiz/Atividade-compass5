CREATE TABLE `oferta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  `dataDeCriacao` date NOT NULL,
  `dataDeValidade` date NOT NULL,
  `desconto` decimal(6,2) NOT NULL,
  `descricao` varchar(120) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `dataDeCriacao` date NOT NULL,
  `dataDeValidade` date NOT NULL,
  `valor` decimal(6,2) NOT NULL,
  `descricao` varchar(120) NOT NULL,
  `ofertas` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `ofertas` FOREIGN KEY (`id`) REFERENCES `oferta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pedido` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(15) NOT NULL,
  `itens` int NOT NULL,
  `total` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `itens` FOREIGN KEY (`id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


drop table pedido;
drop table item;
drop table oferta;