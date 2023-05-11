CREATE TABLE `account` (
  `ID_account` int NOT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  `pass` varchar(45) NOT NULL,
  `typeUser` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `canteen` (
  `ID_canteen` int NOT NULL,
  `ten` varchar(45) NOT NULL,
  `sodienthoai` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ID_diachi` int DEFAULT NULL,
  `PIN` int DEFAULT NULL,
  `avatar` longblob,
  PRIMARY KEY (`ID_canteen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `cart_items` (
  `cart_item_id` int NOT NULL,
  `cart_id` varchar(45) DEFAULT NULL,
  `monan_id` varchar(45) DEFAULT NULL,
  `soluong` varchar(45) DEFAULT NULL,
  `gia` varchar(45) DEFAULT NULL,
  `cart_itemscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `carts` (
  `cart_id` int NOT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `diachi` (
  `ID_diachi` int NOT NULL,
  `ID_nguoidung` int DEFAULT NULL,
  `tinh` int DEFAULT NULL,
  `huyen` int DEFAULT NULL,
  `xa` int DEFAULT NULL,
  PRIMARY KEY (`ID_diachi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `diachi` (
  `ID_diachi` int NOT NULL,
  `ID_nguoidung` int DEFAULT NULL,
  `tinh` int DEFAULT NULL,
  `huyen` int DEFAULT NULL,
  `xa` int DEFAULT NULL,
  PRIMARY KEY (`ID_diachi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `giohoatdong` (
  `ID_giohoatdong` int NOT NULL,
  `ID_canteen` int NOT NULL,
  `thu` int DEFAULT NULL,
  `giomocua` time DEFAULT NULL,
  `giodongcua` time DEFAULT NULL,
  PRIMARY KEY (`ID_giohoatdong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `khachhang` (
  `ID_khachhang` int NOT NULL,
  `hoten` varchar(45) NOT NULL,
  `ngaysinh` date DEFAULT NULL,
  `gioitinh` varchar(45) DEFAULT NULL,
  `chieucao` double DEFAULT NULL,
  `cannang` double DEFAULT NULL,
  `sodienthoai` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ID_canteen` int DEFAULT NULL,
  `yeuthich` varchar(45) DEFAULT NULL,
  `pin` int DEFAULT NULL,
  `avatar` longblob,
  PRIMARY KEY (`ID_khachhang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `Lichsutimkiem` (
  `ID_lichsutimkiem` int NOT NULL,
  `ID_nguoidung` int DEFAULT NULL,
  `noidung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_lichsutimkiem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `loaithucan` (
  `ID_loaithucan` int NOT NULL,
  `loaithucan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_loaithucan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `monan` (
  `ID_monan` int NOT NULL,
  `ID_canteen` int DEFAULT NULL,
  `tenmon` varchar(255) NOT NULL,
  `mota` text,
  `thanhphan` varchar(255) DEFAULT NULL,
  `huongvi` varchar(255) DEFAULT NULL,
  `ID_loaithucan` int DEFAULT NULL,
  `giacu` decimal(10,3) DEFAULT NULL,
  `giahientai` decimal(10,3) NOT NULL,
  `ngaytao` date NOT NULL,
  `hinhanhchinh` longblob,
  `trangthai` int DEFAULT NULL,
  `daban` int DEFAULT NULL,
  `xoa` int DEFAULT NULL,
  PRIMARY KEY (`ID_monan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci