
package Login_test.model;

public class modelHocKy {

    public modelHocKy(int maHocKy, int maNamHoc) {
        this.maHocKy = maHocKy;
        this.maNamHoc = maNamHoc;
    }

    public int getMaHocKy() {
        return maHocKy;
    }

    public void setMaHocKy(int maHocKy) {
        this.maHocKy = maHocKy;
    }

    public int getMaNamHoc() {
        return maNamHoc;
    }

    public void setMaNamHoc(int maNamHoc) {
        this.maNamHoc = maNamHoc;
    }

    @Override
    public String toString() {
        return "Học kỳ " + maHocKy + " Năm học " + maNamHoc;
    }
    private int maHocKy;
    private int maNamHoc;
    
}
