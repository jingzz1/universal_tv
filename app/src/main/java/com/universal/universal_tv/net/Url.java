package com.universal.universal_tv.net;

/**
 * Created by Administrator on 2018/5/17.
 */

public class Url {
    public static final String baseUrl = "http://47.95.207.185:8070/card-mobile";
    public static final String viewfile = baseUrl+"/viewfile/";

    //启动页图片
    public static final String startPage = baseUrl+"/startPage";
    //上传图片
    public static final String uploadPic = baseUrl + "/upload/uploadPic";
    //换肤
    public static final String queryTheme = baseUrl+"/theme/queryTheme";
    //获取最新版本号
    public static final String queryVersion = baseUrl+"/version/queryVersion";


    //获取验证码
    public static final String addVerifyCode = baseUrl +"/message/addVerifyCode";
    //注册
    public static final String register = baseUrl +"/authuser/register";
    //用户登录
    public static final String login = baseUrl+"/userLogin";
    //轮播图 1快卡道 2融易借 3咖乐购 4首页顶部 5首页底部 6最新活动
    public static final String cardAdList = baseUrl+"/cardAdList";
    //忘记密码
    public static final String forgetPwd = baseUrl+"/authuser/forgetPwd";
    //认证信息查询
    public static final String queryAuthUserInfo = baseUrl+"/userAuthInfo/queryAuthUserInfo";

    //修改密码
    public static final String updatePwd = baseUrl + "/authuser/updatePwd";
    //修改手机号
    public static final String changeMobile = baseUrl+"/authuser/changeMobile";
    //设置支付密码
    public static final String setPayPwd = baseUrl+"/authuser/setPayPwd";

    //系统公告
    public static final String noticeList = baseUrl+"/notice/noticeList";

    //上传身份证认证
    public static final String uploadAuthIdCard = baseUrl+"/userPreAuth/uploadAuthIdCard";
    //上传信用卡认证
    public static final String uploadAuthCreditCard = baseUrl+"/userPreAuth/uploadAuthCreditCard";
    //上传储蓄卡认证
    public static final String uploadAuthDepositCard = baseUrl+"/userPreAuth/uploadAuthDepositCard";
    //上传其他认证信息
    public static final String uploadAuthOther = baseUrl+"/userPreAuth/uploadAuthOther";
    //认证信息提交审核
    public static final String doPostAuthAudit = baseUrl+"/userPreAuth/doPostAuthAudit";

    //快卡道查询银行列表
    public static final String kkd_queryList = baseUrl+"/cardfastchannel/queryList";
    //快卡道申请记录
    public static final String kkd_applyRecords = baseUrl+"/cardfastchannel/applyRecords";
    //快卡道银行详情
    public static final String kkd_queryDetail = baseUrl+"/cardfastchannel/queryDetail";
    //快卡道快速申请
    public static final String kkd_applyCard = baseUrl+"/cardfastchannel/applyCard";

    //融易借合作商查询
    public static final String ryj_queryList = baseUrl+"/borrow/queryList";
    //融易借银行卡详情
    public static final String ryj_queryDetail = baseUrl+"/borrow/queryDetail";
    //融易借快速申请
    public static final String ryj_applyBorrow = baseUrl+"/borrow/applyBorrow";
    //融易借通道申请记录查询
    public static final String ryj_applyRecords = baseUrl+"/borrow/applyRecords";
    //获取咖学院分类
    public static final String queryCollegeType = baseUrl + "/cardcollege/queryCollegeType";
    //查询指定分类的咖学院知识库
    public static final String collegeDatas = baseUrl + "/cardcollege/queryCollegeDatas";

    //用户收入明细
    public static final String queryAccountDetail = baseUrl+"/commission/queryAccountDetail";
    //查询用户基本信息
    public static final String queryUserInfo = baseUrl+"/authuser/queryUserInfo";
    //修改昵称
    public static final String updateNickName = baseUrl+"/authuser/updateNickName";


    //我的钱包（查询账户余额）
    public static final String queryAccountBook = baseUrl+"/ord/queryAccountBook";
    //提现
    public static final String applyDesposit = baseUrl+"/desposit/applyDesposit";
    //获取用户账单
    public static final String queryFinanceOrd = baseUrl+"/ord/queryFinanceOrd";
    //添加银行
    public static final String addBankCard = baseUrl+"/bankcard/addBankCard";
    //查询提现支持银行
    public static final String querySupportBankList = baseUrl+"/bankcard/queryBankList";
    //获取银行卡类型，获取bankId
    public static final String queryCardTyoe = baseUrl+"/bankcard/queryCardTyoe";
    //查询银行卡列表
    public static final String queryBankCardList = baseUrl+"/bankcard/queryBankCardList";
    //设置默认银行卡
    public static final String markDefault = baseUrl+"/bankcard/markDefault";
    //删除银行卡
    public static final String deleteBankCard = baseUrl+"/bankcard/deleteBankCard";
    //查询银行
    public static final String queryBankInfo = baseUrl+"/bankcard/queryBankInfo";
    //查询默认银行卡
    public static final String queryDefaultBankCard = baseUrl+"/bankcard/queryDefaultBankCard";

    //查询用户客户关系汇总
    public static final String queryRelationSum = baseUrl+"/customer/queryRelationSum";
    //查询今日新增用户列表
    public static final String queryTodayNewCustomers = baseUrl+"/customer/queryTodayNewCustomers";
    //查询所有用户列表
    public static final String queryAllCustomers = baseUrl+"/customer/queryAllCustomers";
    //查询用户客户详情
    public static final String queryCustomerDetail = baseUrl+"/customer/queryCustomerDetail";
    //查询用户操作记录
    public static final String queryCustomerOperateRecords = baseUrl+"/customer/queryCustomerOperateRecords";

    //查询黄页簿信息
    public static final String queryYellopageDatas = baseUrl+"/cardyellopage/queryYellopageDatas";
    //黄页溥获取服务分类
    public static final String queryServetype = baseUrl+"/cardyellopage/queryServetype";
    //黄页申请
    public static final String applyYellopage = baseUrl+"/cardyellopage/applyYellopage";


    //添加收货地址
    public static final String addReceiveAddress = baseUrl+"/useraddress/addReceiveAddress";
    //查询收货地址
    public static final String queryReceiveAddressList = baseUrl+"/useraddress/queryReceiveAddressList";
    //设置默认地址
    public static final String setDefaultReceiveAddress = baseUrl+"/useraddress/setDefaultReceiveAddress";
    //修改收货地址
    public static final String updateReceiveAddress = baseUrl+"/useraddress/updateReceiveAddress";
    //判断是否有默认地址
    public static final String getDefaultReceiveAddress = baseUrl+"/useraddress/getDefaultReceiveAddress";
    //删除地址
    public static final String deleteReceiveAddress = baseUrl+"/useraddress/deleteReceiveAddress";

    //添加工单
    public static final String addWorkOrder = baseUrl+"/workorder/addWorkOrder";
    //查询工单列表
    public static final String queryWorkOrderList = baseUrl+"/workorder/queryWorkOrderList";
    //查询工单详情
    public static final String queryWorkOrderDetail = baseUrl+"/workorder/queryWorkOrderDetail";
    //工单评价
    public static final String doAppraise = baseUrl+"/workorder/doAppraise";

    //佣金宝明细查询
    public static final String queryCommissionDatas = baseUrl+"/commission/queryCommissionDatas";
    //佣金宝分享展示
    public static final String queryShare = baseUrl+"/share/queryShare";

    //咖乐购商品列表
    public static final String caffeineList= baseUrl +"/caffeineList";
    //咖乐购订单支付
    public static final String klg_orderPay = baseUrl+"/payment/orderPay";
    //咖乐购订单列表
    public static final String caffeineOrderList = baseUrl+"/caffeineOrderList";
    //咖乐购订单详情
    public static final String caffeineOrderDetail = baseUrl+"/caffeineOrderDetail";

    //判断是否有支付密码
    public static final String haveSetPayPwd = baseUrl+"/payment/haveSetPayPwd";
    //校验余额是否充足
    public static final String checkMoneyEnough = baseUrl+"/payment/checkMoneyEnough";

    //查询会员升级列表
    public static final String queryUserRightsList = baseUrl+"/authuser/queryUserRightsList";
    //会员升级支付
    public static final String authUserUpgrade = baseUrl+"/payment/authUserUpgrade";
    //创建预订单
    public static final String createOrder = baseUrl + "/payment/createOrder";

    //账单宝
    //添加信用卡
    public static final String addCreditCard = baseUrl+"/bill/addCreditCard";
    //设备详情
    public static final String billChannelDetail = baseUrl+"/bill/billChannelDetail";
    //账单核对(生成计划)
    public static final String billCheck = baseUrl+"/bill/billCheck";
    //卡片详情
    public static final String cardDetial = baseUrl+"/bill/cardDetial";
    //卡片管理
    public static final String zdbCardList = baseUrl+"/bill/cardList";
    //对账查询
    public static final String check = baseUrl+"/bill/check";
    //账单核对确认
    public static final String confirmBillCheck = baseUrl+"/bill/confirmBillCheck";
    //计划详情
    public static final String planDetial = baseUrl+"/bill/planDetial";
    //查询设备列表
    public static final String queryChannelList = baseUrl+"/bill/queryChannelList";
    //用户卡信息
    public static final String queryUserCard = baseUrl+"/bill/queryUserCard";
    //设备开关
    public static final String updateChannel = baseUrl+"/bill/updateChannel";
    //日程计划
    public static final String dayPlan = baseUrl+"/bill/dayPlan";
    //还款消费计划执行
    public static final String executeExpend = baseUrl+"/bill/executeExpend";
    //上期消费累计
    public static final String lastExpend = baseUrl + "/bill/lastExpend";
    //账单宝佣金宝明细查询
    public static final String queryBillCommission = baseUrl+"/commission/queryBillCommission";
    //账单分享
    public static final String shareBill = baseUrl+"/bill/shareBill";


    //查询备用金
    public static final String queryReserveMoney = baseUrl+"/bill/queryReserveMoney";
    //设置备用金
    public static final String setReserveMoney = baseUrl+"/bill/setReserveMoney";

    //判断是否能进模块
    public static final String checkRights = baseUrl+"/authRights/checkRights";
}
