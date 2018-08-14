package com.example.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * https://www.jianshu.com/p/b81f5e0d3241
 * Created by feng on 2018/8/6.
 * 其实为CoordinatorLayout的子View设置交互行为只需三步：
 * <p>
 * 自定义一个继承自Behavior类的交互行为类；
 * 把观察者的layout_behavior属性设置为自定义行为类的类名；
 * 重写Behavior类的相关方法来实现我们想要的交互行为。
 */
public class FollowBehavior extends CoordinatorLayout.Behavior<TextView> {
    private static final String TAG = FollowBehavior.class.getSimpleName();

    public FollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * View的依赖关系的确定
     * 现在，我们在探究一下交互行为中的两个View之间的依赖关系是怎么确定的。
     * 我们称child为交互行为中根据另一个View的变化做出响应的那个个体，
     * 而dependent view为child所依赖的View。实际上，确立child和dependent view的依赖关系有两种方式：
     * <p>
     * 显式依赖：为child绑定一个Behavior，并在Behavior类的layoutDependsOn()方法中做手脚。
     * 即当传入的dependency为dependent view时返回true，这样就建立了child和dependent view之间的依赖关系。
     *
     * 隐式依赖：通过我们最开始提到的锚（anchor）来确立。具体做法可以这样：在XML布局文件中，
     * 把child的layout_anchor属性设为dependent view的id，然后child的layout_anchorGravity属性用来描述为
     * 它想对dependent view的变化做出什么样的响应。关于这个我们会在后续篇章给出具体示例。
     * 无论是隐式依赖还是显式依赖，在dependent view发生变化时，相应Behavior类的onDependentViewChanged()
     * 方法都会被调用，在这个方法中，我们可以让child做出改变以响应dependent view的变化。
     * <p>
     * 作者：absfree
     * 链接：https://www.jianshu.com/p/b81f5e0d3241
     * 來源：简书
     * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        Log.d(TAG, "layoutDependsOn():");
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        Log.d(TAG, "onDependentViewChanged():");
        child.setX(dependency.getX() + 150);
        child.setY(dependency.getY() + 150);
        return true;
    }

}


