# ScrollIndicator
![image](https://github.com/lx1992lx/ScrollIndicator/blob/master/indicator.gif)
<br><br><br>
## 一个轻量级的ViewPager Indicator。
使用方法
---------
1）添加引用：<br>

     compile 'com.yyxk.luxin:scrollindicator:1.0.1'
     

2)关联ViewPager<br>

    mScrollIndicator.setUpWithViewPager(mViewPager);
    
3)个性化设置<br>
  你可以在xml文件中设置以下属性:<br>
  
          <com.yyxk.customviews.indicator.ScrollIndicator
                android:layout_width="match_parent"
                android:layout_height="30dp"
                android:id="@+id/indicator"
                android:layout_alignParentBottom="true"
                android:layout_marginBottom="50dp"
                app:si_color="@color/white" //设置颜色
                app:si_outRadius="10dp"     //设置外层圈大小
                app:si_innerRadius="7dp"    //设置指示球大小
                app:si_circlePadding="5dp"  //设置球与球的间距
                />

  当然也可以在java代码中设置：<br>
  
       mScrollIndicator.setBuild(new IndicatorBuilder()
                    .setCirclePadding(15)
                    .setColor(Color.WHITE)
                    .setInnerRaidus(20)
                    .setOutRadius(30));
