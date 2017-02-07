package rfh.tianli.com.rfh.widget.pulltorefresh;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 9:34.
 * 描述：上拉下拉接口
 */

public interface Pullable {
    /**
     * 判断是否可以下拉，如果不需要下拉功能可以直接return false
     *
     * @return true如果可以下拉否则返回false
     */
    boolean canPullDown();

    /**
     * 判断是否可以上拉，如果不需要上拉功能可以直接return false
     *
     * @return true如果可以上拉否则返回false
     */
    boolean canPullUp();
}
