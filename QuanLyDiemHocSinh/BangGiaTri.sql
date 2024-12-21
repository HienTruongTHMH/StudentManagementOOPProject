--Bảng Khoa
CREATE TABLE Khoa (
    MaKhoa INT PRIMARY KEY,
    TenKhoa NVARCHAR(100) NOT NULL,
	ChuongTrinhDaoTao NVARCHAR(20)
);
--Bảng năm học
CREATE TABLE NamHoc (
    MaNamHoc INT PRIMARY KEY,
    TenNamHoc NVARCHAR(50),
    NamHocBatDau VARCHAR(10),
    NamHocKetThuc VARCHAR(10)
);
--Bảng HocKy
CREATE TABLE HocKy (
    MaHocKy INT PRIMARY KEY,
    TenHocKy NVARCHAR(50),
    ThoiGianBatDau DATE,
    ThoiGianKetThuc DATE	
);

-- Bảng GiangVien
CREATE TABLE GiangVien (
    MaGV INT PRIMARY KEY,
	MaKhoa Int,
    HoVaTen NVARCHAR(60) NOT NULL,
	CCCD Int NOT NULL,
    NgaySinh DATE NOT NULL,
    GioiTinh CHAR(1) NOT NULL,
    Gmail VARCHAR(100) UNIQUE not null,
    SoDienThoai VARCHAR(15),
    DiaChi NVARCHAR(255),
	NoiSinh NVARCHAR(200),
	DanToc NVarChar(20),
	QuocTich NVarChar(30)
	FOREIGN KEY (MaKhoa) REFERENCES Khoa(MaKhoa)
);

-- Bảng TKGiangVien
CREATE TABLE TKGiangVien (
    MaGV INT,
    TaiKhoan VARCHAR(50) NOT NULL,
    MatKhau VARCHAR(50) NOT NULL,
	Status VARCHAR(15)
    FOREIGN KEY (maGV) REFERENCES GiangVien(MaGV)
);

-- Bảng SinhVien
CREATE TABLE SinhVien (
    MaSV INT PRIMARY KEY,
    HoVaTen NVARCHAR(60) NOT NULL,
	CCCD CHAR(15),
    MaKhoa INT NOT NULL,
	NgaySinh Date,
    DiaChi TEXT,
    GioiTinh CHAR(1),
    SDT VARCHAR(15),
    Gmail VARCHAR(100) UNIQUE Not null,
	NoiSinh NVarChar(100),
	DanToc NVarChar(20),
	QuocTich NVarChar(30)
    FOREIGN KEY (MaKhoa) REFERENCES Khoa(MaKhoa)
);

-- Bảng TKSinhVien
CREATE TABLE TKSinhVien (
    maSV INT,
    TaiKhoan VARCHAR(50) NOT NULL,
    MatKhau VARCHAR(50) NOT NULL,
	Status VARCHAR(15)
    FOREIGN KEY (maSV) REFERENCES SinhVien(maSV)
);


-- Bảng HocPhan
CREATE TABLE HocPhan (
    MaHP INT PRIMARY KEY,
	MaHocKy INT FOREIGN KEY REFERENCES HocKy(MaHocKy),
	MaNamHoc INT FOREIGN KEY REFERENCES NamHoc(MaNamHoc),
    TenHocPhan NVARCHAR(100) NOT NULL,
    TinChi INT NOT NULL,
    TinChi_HocPhi DECIMAL(10, 2)
);

-- Bảng LopChuyenNganh
CREATE TABLE LopChuyenNganh (
    MaLopChuyenNganh INT PRIMARY KEY,
    MaGV INT NOT NULL,
    TenLopChuyenNganh NVARCHAR(100) NOT NULL,
    FOREIGN KEY (MaGV) REFERENCES GiangVien(MaGV)
);

--Bảng SinhVien_LopChuyenNganh
Create Table SinhVien_LopChuyenNganh(
	MaSV INT,
	MaLopChuyenNganh INT,
	PRIMARY KEY(MaSV, MaLopChuyenNganh),
	FOREIGN KEY (MaSV) REFERENCES SinhVien(MaSV),
	FOREIGN KEY (MaLopChuyenNganh) REFERENCES LopChuyenNganh(MaLopChuyenNganh)
);
-- Bảng LopHP
CREATE TABLE LopHP (
    MaLopHP INT PRIMARY KEY,
    MaHP INT NOT NULL,
    MaGV INT NOT NULL,
    TenLop NVARCHAR(50),
    SoLuong INT NOT NULL,
	PhongHoc NVARCHAR(50),
	MaHocKy INT FOREIGN KEY REFERENCES HocKy(MaHocKy),
	MaNamHoc INT FOREIGN KEY REFERENCES NamHoc(MaNamHoc),
	TrangThai NVARCHAR(50),
    FOREIGN KEY (MaHP) REFERENCES HocPhan(MaHP),
    FOREIGN KEY (MaGV) REFERENCES GiangVien(MaGV)
);

--Bảng LớpHP chứa các học sinh
CREATE TABLE SinhVien_LopHP (
    MaSV INT FOREIGN KEY REFERENCES SinhVien(MaSV),
    MaLopHP INT FOREIGN KEY REFERENCES LopHP(MaLopHP),
    PRIMARY KEY (MaSV, MaLopHP)
);

-- Bảng Điểm
CREATE TABLE Diem (
    MaSV INT NOT NULL,
    MaHP INT NOT NULL,
    MaHocKy INT FOREIGN KEY REFERENCES HocKy(MaHocKy),
	MaNamHoc INT FOREIGN KEY REFERENCES NamHoc(MaNamHoc),
	DiemCC DECIMAL(4,2),
    DiemBT DECIMAL(4,2),
    DiemCH DECIMAL(4,2),
    DiemDA DECIMAL(4,2),
    DiemGK DECIMAL(4,2),
    DiemCK DECIMAL(4,2),
    DiemT10 DECIMAL(4,2),
    DiemChu VARCHAR(5),
    KiemTra BIT DEFAULT 0,
    PRIMARY KEY (maSV, maHP),
    FOREIGN KEY (MaSV) REFERENCES SinhVien(MaSV),
    FOREIGN KEY (MaHP) REFERENCES HocPhan(MaHP)
);

-- Bảng KetQuaHT
CREATE TABLE TongKetQuaHT (
    MaSV INT,
	MaHocKy INT FOREIGN KEY REFERENCES HocKy(MaHocKy),
	MaNamHoc INT FOREIGN KEY REFERENCES NamHoc(MaNamHoc),
    TinChiTichLuy INT,
	TinChiHocLai INT,
    GPA DECIMAL(4, 2),
    CPA DECIMAL(4, 2),
	DiemTBCHocBong DECIMAL(4,2),
    DiemRenLuyen DECIMAL(4,2),
	XepLoaiHocTap NVARCHAR(10),
	PRIMARY KEY(MaSV, MaHocKy, MaNamHoc),
    FOREIGN KEY (MaSV) REFERENCES SinhVien(MaSV)
);
