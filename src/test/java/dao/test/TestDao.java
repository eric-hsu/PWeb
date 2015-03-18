package dao.test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jinxin.common.sql.CommenSql;
import com.jinxin.common.test.SpringTransactionalContextTests;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.BankConfig;
import com.jinxin.model.persistence.TBank;
import com.jinxin.model.persistence.TBankTag;
import com.jinxin.model.persistence.UnNormalProcess;


/**
 * @className:TestDao.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-25 下午2:49:58
 */

public class TestDao extends SpringTransactionalContextTests{
	@Autowired
	private BaseDaoI baseDao;
    //@Test
	public void saveTest(){
    	System.out.println("=======開始測試===save====");
		TBank tBank = new TBank();
		tBank.setBankcode("post");
		tBank.setBankname("中国邮政储蓄银行");
		tBank.setCreatetime(new Date());
		tBank.setCreatetor("eric");
		
//		Traderecord traderecord = new Traderecord();
//		traderecord.setTrNo("1245");//交易流水号
//		traderecord.setTrMerOrderno("55558");// 商户订单号;
//		traderecord.setTrMerNo(10000l);//商户号
//		traderecord.setTrGwNo(1111l);// 网关接入号;
//		traderecord.setTrCurrency("CNY");//交易币种
//		traderecord.setTrAmount(new BigDecimal(1000));//交易金额
//		traderecord.setTrStatus(1);
//		traderecord.setTrTradeRate(new BigDecimal(12));//交易费率
//		traderecord.setTrReseverRate(new BigDecimal(10));// 保证金扣率;
//		traderecord.setTrRateValue(new BigDecimal(1));// 交易汇率;
//		traderecord.setTrBankcurrency("CNY");// 收单币种 银行通道接收币种;
//		traderecord.setTrBankamout(new BigDecimal(20));// 收单金额;
//		traderecord.setTrBankCode("ABC");// 支付银行代码;
//		traderecord.setTrChaCode(120l);// 支付通道代码;
//		traderecord.setTrIsdelay(10);// 是否延时通道 1 : 是; 0 : 否;
//		traderecord.setTrChaRate(new BigDecimal(10));// 通道扣率 与银行结算时使用
//		traderecord.setTrPaystarttime(new Date());// 指调用网关接口支付的时间(即插入异常信息表胡时间);
//		traderecord.setTrDatetime(new Date());// 指插入本交易表的时间（即提交银行通道时间）
//		traderecord.setTrSubmiturl("pay");// 提交地址;指哪个支付域名; 51paypay
//		traderecord.setTrChecked(1);// 勾兑 0 : 未勾对;1 : 已勾对; 
//		traderecord.setTrRefundment(1);// 退款 0 :为未退款;1 :为已退款 ;
//		traderecord.setTrProtest(1);// 拒付 0 :为未拒付;1:为已拒付;
//		traderecord.setTrCongeal(1);// 冻结 0 :为未冻结;1:为已冻结;
//		traderecord.setTrDelivery(1);// 妥投 0 :为未妥投;1:为已妥投;
//		traderecord.setTrTsId(1l);// 交易划款ID存放第一次划款的ID,第二次以后存放于异常表;
//		traderecord.setTrTsStatus(1);// 交易划款状态; 0: 未划款; 1:已制表 ; 2:已划款
//		traderecord.setTrTsId(1l);// 保证金划款ID;
//		traderecord.setTrTsStatus(2);// 保证金划款状态; 0: 未划款; 1:已制表 ; 2:已划款
//		traderecord.setTrAgentNo(123l);// 代理商号;
//		traderecord.setTrAsId(1l);// 代理商划款ID;
//		traderecord.setTrAsStatus(1);// 代理商划款状态; 0: 未划款; 1:已制表 ; 2:已划款
//		traderecord.setTrIsdcc(1);// 1 : DCC ; 0 : EDC;
//		traderecord.setTrTsBatch(1l);// 划款审单批次号
//		traderecord.setTrIstscheck(1);// 1 : 是; 0 : 否;
//		traderecord.setTrIsrscheck(1);// 1 : 是; 0 : 否;
//		traderecord.setTrIsdaycheck(1);// 风控需要 0 : 未审核; 1 : 确认无风险;2: 确认有风险;
//		traderecord.setTrInfType(1);// 拒付申述 -2 未申诉 -1 申诉中  0:失败;1:成功 ;
//		traderecord.setTrRsId(1l);// 保证金划款ID;
//		traderecord.setTrRsStatus(1);// 保证金划款状态; 0: 未划款; 1:已制表 ; 2:已划款
//		traderecord.setTrIsexplain(1);// 拒付申述 -2 未申诉 -1 申诉中  0:失败;1:成功 ;
//		traderecord.setTrIsfraud(1);//1 : 是; 0 : 否;伪冒状态
//		traderecord.setTrIscomplain(1);//1 : 是; 0 : 否; 投诉状态
//		traderecord.setTrIsLock(1);//是否锁定 1 : 是; 0 : 否
//		traderecord.setTrFEEFailMER(1);//失败订单是否收取手续费(针对商户) 1 :是 0:否
//		traderecord.setTrFEESuccessMER(1);//划款前成功订单全额异常是否收取手续费(针对商户) 1 :是 0:否
		try{
		//long id = (Long)baseDao.save(tOrder);
		//System.out.println("=======結束測試===save====id="+id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    //@Test
   	public void findTest(){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("orderno", "5555555555");
    	try{
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
   	
    //@Test
   	public void findPageTest(){

    	try{
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
   	
    //@Test
    public void getTest(){
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("orderno", "123456");
//		//Order order = (Order) baseDao.get("from Order t where t.orderno = :orderno and t.id != '0'",params);
//		TOrder tOrder = (TOrder) baseDao.get(TOrder.class,2222l);
//    	String sql = InterfaceSql.FIND_TRADERECORD;
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("orderno", "20140723142300833358");
//    	UnNormalProcess order = (UnNormalProcess) baseDao.get("from UnNormalProcess t where  t.traderecord.TR_NO = :orderno ",params);
//
//    	System.out.println("=======結束測試======="+order.getUpTsId());
    	List<BankConfig>  list0 = new ArrayList<BankConfig>();
    	
		
    	StringBuffer infoSql = new StringBuffer(
				CommenSql.FIND_MER_CHANNEL_INFO);
		List list = baseDao.find("select m.mcMerNo,m.mcGwNo,c.bank.bankName,c.bank.bankCode ,c.chaCode,c.chaName,c.chaThreeparty,c.chaTwoparty,c.chaTwofiveparty from Channel c,MerChannel m where c.chaCode = m.mcChaCode and m.mcMerNo=10000");
		for(int i=0;i<list.size();i++){
			BankConfig bankConfig = new BankConfig();
			Object[] datas = (Object[]) list.get(i);

			bankConfig.setMcMerNo(datas[0].toString());
			bankConfig.setMcGwNo(datas[1].toString());
			bankConfig.setBankname(datas[2].toString());
			bankConfig.setBankcode(datas[3].toString());
			bankConfig.setChaCode(datas[4].toString());
			bankConfig.setChaName(datas[5].toString());
			String type="";
			String threep = datas[6].toString();
			if("1".equals(threep)){
				type = type+"3"+"|";
			}
			
			String twop = datas[7].toString();
			if("1".equals(twop)){
				type = type+"2"+"|";
			}
			
			String twofivep = datas[8].toString();
			if("1".equals(twofivep)){
				type = type+"2.5"+"|";
			}
			list0.add(bankConfig);
		}
		System.out.println("list0："+list0.size());
    }
    
   @Test
    public void saveOrUpdateTest(){
    	try{
    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    //@Test
    public void deleteTest(){
    	// Order order = (Order) baseDao.get(Order.class,155l);
    	
    }
    
    //@Test
    public void countTest(){
    	
    	try{
    		long count = baseDao.count("select count(*) from Order o");
    		System.out.println("count = "+ count);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
}
