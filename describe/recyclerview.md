
1、ScrollView 嵌套 recyclerView 显示不完整，或者只显示 recyclerView 的两条数据。
方法：
--使用 android.support.v4.widget.NestedScrollView 替换 ScrollView。
--LinearLayoutManager layout = new LinearLayoutManager(getContext()) {
      @Override
      public boolean canScrollVertically() {
          return false;//禁止滑动
      }
  };
  recyclerView.setLayoutManager(layout);