package com.java8.lambda.chapter8;

/**
 * 	使用传统方式实现模板方法模式
 *	
 *	@author hzweiyongqiang
 */
public class Course14TemplateMethodPattern1 {


	/**
	 *	异常抛出
	 */
	public class ApplicationDenied extends Exception{
		private static final long serialVersionUID = 1L;
		
	}
	/**
	 * 	抽象类 LoanApplication 来控制算法结构
	 * 	该类包含一些贷款调查结果报告的通用代码。
	 */
	public abstract class LoanApplication{
		public void checkLoanApplication() throws ApplicationDenied{
			
		}
		protected abstract void checkIdentity() throws ApplicationDenied;
		protected abstract void checkIncomeHistory() throws ApplicationDenied;
		protected abstract void checkCreditHistory() throws ApplicationDenied;
		protected void reportFindings() {};
	}
	
	class CompanyLoanApplication extends LoanApplication{
		/** checkIdentity 方法在 Companies House 等注册公司数据库中查找相关信息。 **/
		@Override
		protected void checkIdentity() throws ApplicationDenied {
			// TODO Auto-generated method stub
		}
		/** checkIncomeHistory 方法评估公司的现有利润、损益表和资产负债表。 **/
		@Override
		protected void checkIncomeHistory() throws ApplicationDenied {
			// TODO Auto-generated method stub
		}
		/** checkCreditHistory 方法则查看现有的坏账和未偿债务。 **/
		@Override
		protected void checkCreditHistory() throws ApplicationDenied {
			// TODO Auto-generated method stub
		}
	}
	
	class PersonalLoanApplication extends LoanApplication{
		/**  checkIdentity 方法通过分析客户提供的纸本结算单，确认客户地址是否真实有效。 **/
		@Override
		protected void checkIdentity() throws ApplicationDenied {
			// TODO Auto-generated method stub
		}
		/**  checkIncomeHistory 方法通过检查工资条判断客户是否仍被雇佣。 **/
		@Override
		protected void checkIncomeHistory() throws ApplicationDenied {
			// TODO Auto-generated method stub
		}
		/** checkCreditHistory 方法则会将工作交给外部的信用卡支付提供商。 **/
		@Override
		protected void checkCreditHistory() throws ApplicationDenied {
			// TODO Auto-generated method stub
		}
	}
	
	/**
	 * 	员工申请贷款是个人申请的一种特殊情况
	 */
	class EmployeeLoanApplication extends PersonalLoanApplication{
		@Override
		protected void checkIncomeHistory() throws ApplicationDenied {
			// 这是自己人 !
		}
	}
}
