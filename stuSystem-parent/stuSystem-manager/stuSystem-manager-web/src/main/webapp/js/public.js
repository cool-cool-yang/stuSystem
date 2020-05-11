/*  使用jQuery的时间选择控件*/
/*在页面加时便初始化函数*/
$(function () {
    $(".input02").datepicker({
        numberOfMonths:1,   /*设置显示几个月*/
        showButtonPanel:true,  /*是否显示按钮面板*/
        prevButton: true,  /* 是否显示上个月按钮*/
        nextButton:true,   /* 是否显示下月按钮*/
        dateFormat:'yy-mm-dd',    /*日期格式*/
        clearText:"清除",     /*清除日期的按钮名称*/
        closeText:"关闭",    /*关闭按钮的名称*/
        prevText:'<上月', /*上月按钮*/
        nextText:'下月>', /*下月按钮*/
        yearSuffix:'年',    /* 年的后缀*/
        showMonthAfterYear:true,    /*是否把月放在年的后面*/
        monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'], /*月的名称*/
        monthNamesShort: ['一','二','三','四','五','六', '七','八','九','十','十一','十二'],  /*月的简称*/
        dayNames:['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  /* 星期几的名称和顺序*/
        dayNamesShort:['周日','周一','周二','周三','周四','周五','周六'], /*星期的简称*/
        dayNamesMin:['日','一','二','三','四','五','六'] /* 最简称*/
    });
});