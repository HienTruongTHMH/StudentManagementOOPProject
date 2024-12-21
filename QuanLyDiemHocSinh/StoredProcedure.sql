--Stored ProceDure tính điểm học sinh
CREATE PROCEDURE CalculateDiemByStudent
    @MaSV INT,
    @MaHP INT
AS
BEGIN
    UPDATE Diem
    SET 
        DiemT10 = (DiemCK * 0.5) + (DiemGK * 0.3) + ((DiemCC + DiemBT + DiemCH + DiemDA) * 0.05),
        DiemChu = CASE 
                    WHEN DiemT10 >= 9.5 THEN 'A+'
                    WHEN DiemT10 >= 8.5 THEN 'A'
                    WHEN DiemT10 >= 8.0 THEN 'B+'
                    WHEN DiemT10 >= 7.0 THEN 'B'
                    WHEN DiemT10 >= 6.5 THEN 'C+'
                    WHEN DiemT10 >= 5.5 THEN 'C'
                    WHEN DiemT10 >= 5.0 THEN 'D+'
                    WHEN DiemT10 >= 4.0 THEN 'D'
                    ELSE 'F'
					END
    WHERE MaSV = @MaSV AND MaHP = @MaHP;
END;
EXEC CalculateDiemByStudent 1, 1;
EXEC CalculateDiemByStudent 1, 2;
EXEC CalculateDiemByStudent 1, 3;
EXEC CalculateDiemByStudent 1, 4;
EXEC CalculateDiemByStudent 1, 5;
EXEC CalculateDiemByStudent 2, 1;
EXEC CalculateDiemByStudent 2, 2;
EXEC CalculateDiemByStudent 2, 3;
EXEC CalculateDiemByStudent 3, 4;