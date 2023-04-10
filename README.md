CREATE TABLE `account` (
  `id` int NOT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  `pass` varchar(45) NOT NULL,
  `typeUser` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `canteen` (
  `id` int NOT NULL,
  `ten` varchar(45) NOT NULL,
  `sodienthoai` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tinh` varchar(45) DEFAULT NULL,
  `huyen` varchar(45) DEFAULT NULL,
  `xa` varchar(45) DEFAULT NULL,
  `PIN` varchar(45) DEFAULT NULL,
  `avatar` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `khachhang` (
  `idkh` int NOT NULL,
  `hoten` varchar(45) NOT NULL,
  `ngaysinh` date DEFAULT NULL,
  `gioitinh` varchar(45) DEFAULT NULL,
  `chieucao` varchar(45) DEFAULT NULL,
  `cannang` varchar(45) DEFAULT NULL,
  `sodienthoai` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `idcantin` varchar(45) DEFAULT NULL,
  `monyeuthich` varchar(45) DEFAULT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `avatar` longblob,
  PRIMARY KEY (`idkh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `Lichsutimkiem` (
  `id` int NOT NULL,
  `idkh` varchar(255) DEFAULT NULL,
  `noidung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `monan` (
  `id` int NOT NULL,
  `idcantin` int DEFAULT NULL,
  `tenmon` varchar(255) NOT NULL,
  `mota` text,
  `huongvi` varchar(255) DEFAULT NULL,
  `loaithucan` varchar(255) DEFAULT NULL,
  `giacu` decimal(10,2) DEFAULT NULL,
  `giahientai` decimal(10,2) NOT NULL,
  `ngaytao` date NOT NULL,
  `hinhanhchinh` longblob,
  `trangthai` varchar(45) DEFAULT NULL,
  `daban` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
