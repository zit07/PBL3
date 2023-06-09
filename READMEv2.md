CREATE TABLE `account` (
  `ID_account` int NOT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  `pass` varchar(45) NOT NULL,
  `typeUser` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_account`)
);

CREATE TABLE `diachi` (
  `ID_diachi` int NOT NULL,
  `tinh` int DEFAULT NULL,
  `huyen` int DEFAULT NULL,
  `xa` int DEFAULT NULL,
  PRIMARY KEY (`ID_diachi`)
) ;



CREATE TABLE `canteen` (
  `ID_canteen` int NOT NULL,
  `ten` varchar(45) NOT NULL,
  `sodienthoai` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ID_diachi` int DEFAULT NULL,
  `PIN` int DEFAULT NULL,
  `avatar` longblob,
  PRIMARY KEY (`ID_canteen`),
  FOREIGN KEY (ID_diachi) REFERENCES diachi(ID_diachi)
);

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
  PRIMARY KEY (`ID_khachhang`),
  FOREIGN KEY (ID_canteen) REFERENCES  canteen(ID_canteen)
) ;



CREATE TABLE `loaithucan` (
  `ID_loaithucan` int NOT NULL,
  `loaithucan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_loaithucan`)
);

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
  PRIMARY KEY (`ID_monan`),
  FOREIGN KEY (ID_canteen) REFERENCES  canteen(ID_canteen),
  FOREIGN KEY (ID_loaithucan) REFERENCES  loaithucan(ID_loaithucan)
);
CREATE TABLE `carts` (
  `cart_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  FOREIGN KEY (user_id) REFERENCES khachhang(ID_khachhang)
) ;

CREATE TABLE `cart_items` (
  `cart_item_id` int NOT NULL,
  `cart_id` int DEFAULT NULL,
  `monan_id` int DEFAULT NULL,
  `soluong` varchar(45) DEFAULT NULL,
  `gia` varchar(45) DEFAULT NULL,
  `cart_itemscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`),
  FOREIGN KEY (cart_id) REFERENCES carts(cart_id),
  FOREIGN KEY (monan_id) REFERENCES monan(ID_monan)
);

CREATE TABLE `giohoatdong` (
  `ID_giohoatdong` int NOT NULL,
  `ID_canteen` int NOT NULL,
  `thu` int DEFAULT NULL,
  `giomocua` time DEFAULT NULL,
  `giodongcua` time DEFAULT NULL,
  PRIMARY KEY (`ID_giohoatdong`),
    FOREIGN KEY (ID_canteen) REFERENCES  canteen(ID_canteen)
);


CREATE TABLE `Lichsutimkiem` (
  `ID_lichsutimkiem` int NOT NULL,
  `ID_nguoidung` int DEFAULT NULL,
  `noidung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_lichsutimkiem`),
    FOREIGN KEY (ID_nguoidung) REFERENCES  account(ID_account)
) ;

CREATE TABLE `doanhthu` (
  `ID_doanhthu` int NOT NULL,
  `ID_canteen` int DEFAULT NULL,
  `ngay` date DEFAULT NULL,
  `soluongdaban` int DEFAULT NULL,
  `tongdoanhthu` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`ID_doanhthu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

