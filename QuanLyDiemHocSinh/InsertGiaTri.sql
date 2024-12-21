INSERT INTO Khoa (MaKhoa, TenKhoa, ChuongTrinhDaoTao) VALUES  
(1, 'Khoa Công Nghệ Thông Tin', 'CSE1000'),  
(2, 'Khoa Điện Tử Viễn Thông', 'DTVT3000'),  
(3, 'Khoa Cơ Khí', 'CK2325'),  
(4, 'Khoa Kinh Tế', 'IBM1111'),  
(5, 'Khoa Ngoại Ngữ', 'EI1717');
INSERT INTO NamHoc (MaNamHoc, TenNamHoc, NamHocBatDau, NamHocKetThuc) VALUES  
(1, 'Năm Học 2024-2025', '2024', '2025'),  
(2, 'Năm Học 2025-2026', '2025', '2026'),  
(3, 'Năm Học 2026-2027', '2026', '2027');  

INSERT INTO HocKy (MaHocKy, TenHocKy, ThoiGianBatDau, ThoiGianKetThuc) VALUES  
(1, 'Học Kỳ 1', '2024-09-02', '2025-1-5'),  
(2, 'Học Kỳ 2', '2025-01-10', '2026-05-31'),  
(3, 'Học Kỳ 3', '2024-06-01', '2024-08-31');  

INSERT INTO GiangVien (MaGV, MaKhoa, HoVaTen, CCCD, NgaySinh, GioiTinh, Gmail, SoDienThoai, DiaChi, NoiSinh, DanToc, QuocTich) VALUES  
(1, 1, 'Nguyễn Văn A', 123456789, '1980-01-01', 'M', 'nguyenvana@example.com', '0901234567', '123 Đường ABC', 'Hà Nội', 'Kinh', 'VietNam'),  
(2, 2, 'Trần Thị B', 987654321, '1985-02-02', 'F', 'tranthib@example.com', '0902345678', '456 Đường DEF', 'Đà Nẵng', 'Nùng', 'VietNam'),  
(3, 3, 'Lê Văn C', 456789123, '1990-03-03', 'M', 'levanc@example.com', '0903456789', '789 Đường GHI', 'Hải Phòng', 'Tày', 'VietNam'),  
(4, 4, 'Phạm Thị D', 321654987, '1983-04-04', 'F', 'phamthid@example.com', '0904567890', '123 Đường JKL', 'Hà Nội', 'Kinh', 'VietNam'),  
(5, 5, 'Đỗ Văn E', 654321789, '1978-05-05', 'M', 'dovanE@example.com', '0905678901', '789 Đường MNO', 'TP.HCM', 'Khmer', 'VietNam');
INSERT INTO TKGiangVien (MaGV, TaiKhoan, MatKhau, Status) VALUES  
(1, 'gvA', '234', 'active'),  
(2, 'gvB', '234', 'active'),  
(3, 'gvC', '234', 'active'),  
(4, 'gvD', '234', 'inactive'),  
(5, 'gvE', '234', 'active');
INSERT INTO SinhVien (MaSV, HoVaTen, CCCD, MaKhoa, NgaySinh, DiaChi, GioiTinh, SDT, Gmail, NoiSinh, DanToc, QuocTich) VALUES  
(1, 'Nguyễn Văn F', '123456000000', 1, '2000-01-01', '101 Đường 123', 'M', '0906789012', 'nguyenf@example.com', 'TP.HCM', 'Kinh', 'VietNam'),  
(2, 'Trần Thị G', '123456000001', 2, '2001-02-02', '102 Đường 456', 'F', '0907890123', 'trang@example.com', 'Hà Nội', 'Kinh', 'VietNam'),  
(3, 'Lê Văn H', '123456000002', 3, '2002-03-03', '103 Đường 789', 'M', '0908901234', 'levanh@example.com', 'Đà Nẵng', 'Kinh', 'VietNam'),  
(4, 'Phạm Thị I', '123456000003', 4, '2003-04-04', '104 Đường 101', 'F', '0900123456', 'phamthi@example.com', 'Hải Phòng', 'Ê Đê', 'VietNam'),  
(5, 'Đỗ Văn J', '123456000004', 5, '2004-05-05', '105 Đường 121', 'M', '0901234567', 'dovanJ@example.com', 'TP.HCM', 'HMong', 'VietNam');
INSERT INTO TKSinhVien (maSV, TaiKhoan, MatKhau, Status) VALUES  
(1, 'svA', '123', 'active'),  
(2, 'svB', '123', 'active'),  
(3, 'svC', '123', 'active'),  
(4, 'svD', '123', 'inactive'),  
(5, 'svE', '123', 'active');
INSERT INTO HocPhan (MaHP, MaHocKy, MaNamHoc, TenHocPhan, TinChi, TinChi_HocPhi) VALUES  
(1, 1, 1, 'Lập Trình C', 2, 500000),  
(2, 1, 1, 'Cấu Trúc Dữ Liệu', 5, 500000),  
(3, 2, 1, 'Mạng Máy Tính', 3, 500000),  
(4, 2, 1, 'Hệ Điều Hành', 4, 500000),  
(5, 3, 1, 'Kinh Tế Học', 2, 500000);
INSERT INTO LopChuyenNganh (MaLopChuyenNganh, MaGV, TenLopChuyenNganh) VALUES  
(1, 1, 'CSE23'),  
(2, 2, 'THM22'),  
(3, 3, 'SE1'),  
(4, 4, 'IBM2'),  
(5, 5, 'DDM24');
INSERT INTO SinhVien_LopChuyenNganh (MaSV, MaLopChuyenNganh)
VALUES
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 3),
    (5, 5);
INSERT INTO LopHP (MaLopHP, MaHP, MaGV, TenLop, SoLuong, PhongHoc, MaHocKy, MaNamHoc, TrangThai) VALUES  
(1, 1, 1, 'Lớp Lập Trình C - A', 30, 'A508', 1, 1, 'Open'), 
(2, 2, 1, 'Lớp Cấu Trúc Dữ Liệu - A', 35, 'A609',1, 1, 'Open'),  
(3, 3, 1, 'Lớp Mạng Máy Tính - A', 25, 'B809',2, 1, 'Close'),  
(4, 4, 4, 'Lớp Hệ Điều Hành - A', 40, 'B809', 2, 1, 'Open'),  
(5, 5, 5, 'Lớp Kinh Tế Học - A', 20, 'B204', 3, 1, 'Close');
INSERT INTO SinhVien_LopHP (MaSV, MaLopHP)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (2, 2),
	(1, 2),
	(3, 2),
	(1, 3),
	(2, 3);
INSERT INTO Diem (MaSV, MaHP, MaHocKy, MaNamHoc, DiemCC, DiemBT, DiemCH, DiemDA, DiemGK, DiemCK, DiemT10, DiemChu, KiemTra)
VALUES
  (1, 1, 1, 1, 9.0, 8.5, 7.5, 9.0, 9.0, 8.0, 8.5, 'B', 1),
  (1, 2, 1, 1, 7.5, 8.0, 9.0, 7.0, 8.5, 8.0, 8.0, 'B', 1),
  (1, 3, 1, 1, 9.0, 8.0, 9.0, 10.0, 7.5, 8.0, 8.0, 'B', 1),
  (1, 4, 2, 1, 7.5, 8.0, 9.0, 7.0, 8.5, 8.0, 8.0, 'B', 1),
  (1, 5, 3, 1, 7.5, 8.0, 9.0, 7.0, 8.5, 8.0, 8.0, 'B', 1),
  (2, 1, 1, 1, 8.0, 7.5, 8.5, 9.0, 9.0, 7.5, 8.2, 'B', 0),
  (2, 2, 2, 1, 8.0, 7.5, 8.5, 9.0, 9.0, 7.5, 8.2, 'B', 0),
  (2, 3, 2, 1, 6.5, 7.0, 8.0, 7.5, 8.0, 8.5, 7.6, 'C', 1),
  (3, 4, 2, 1, 5.5, 6.0, 7.0, 6.5, 7.0, 6.0, 6.4, 'D', 1);

INSERT INTO TongKetQuaHT (MaSV, MaHocKy, MaNamHoc, TinChiTichLuy, TinChiHocLai, GPA, CPA, DiemTBCHocBong, DiemRenLuyen, XepLoaiHocTap)
SELECT  
    d.MaSV,
    d.MaHocKy,
    d.MaNamHoc,
    COUNT(DISTINCT d.MaHP) AS TinChiTichLuy, 
    SUM(CASE WHEN d.DiemCC < 5 OR d.DiemBT < 5 OR d.DiemCH < 5 OR d.DiemDA < 5 OR d.DiemGK < 5 OR d.DiemCK < 5 THEN 1 ELSE 0 END) AS TinChiHocLai,
    AVG((d.DiemCC + d.DiemBT + d.DiemCH + d.DiemDA + d.DiemGK + d.DiemCK) / 6) AS GPA,
    AVG((d.DiemCC + d.DiemBT + d.DiemCH + d.DiemDA + d.DiemGK + d.DiemCK) / 6) AS CPA,
    AVG((d.DiemCC + d.DiemBT + d.DiemCH + d.DiemDA + d.DiemGK + d.DiemCK) / 6) AS DiemTBCHocBong,
    CASE  
        WHEN d.MaSV = 1 THEN 89.0  
        WHEN d.MaSV = 2 THEN 89.0  
        WHEN d.MaSV = 3 THEN 78.0  
        WHEN d.MaSV = 4 THEN 77.5  
        WHEN d.MaSV = 5 THEN 90.0
        ELSE NULL  
    END AS DiemRenLuyen,
    CASE  
        WHEN AVG((d.DiemCC + d.DiemBT + d.DiemCH + d.DiemDA + d.DiemGK + d.DiemCK) / 6) >= 9.5 THEN 'Xuất sắc'
		WHEN AVG((d.DiemCC + d.DiemBT + d.DiemCH + d.DiemDA + d.DiemGK + d.DiemCK) / 6) >= 8.0 THEN 'Giỏi' 
        WHEN AVG((d.DiemCC + d.DiemBT + d.DiemCH + d.DiemDA + d.DiemGK + d.DiemCK) / 6) >= 7.0 THEN 'Khá'  
        WHEN AVG((d.DiemCC + d.DiemBT + d.DiemCH + d.DiemDA + d.DiemGK + DiemCK) / 6) >= 5.0 THEN 'Trung bình'  
        ELSE 'Yếu'  
    END AS XepLoaiHocTap
FROM Diem d
GROUP BY d.MaSV, d.MaHocKy, d.MaNamHoc;