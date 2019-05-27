package math;

/**
 * @author BlackSugar
 * @date 2019/5/27
 */
public class VersionControl {
    protected boolean isBadVersion(int version) {
        return version >= 1150769282;
    }

    public static void main(String[] args) {
        System.out.println(new VersionControl());
    }
}
