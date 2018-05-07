package com.java8.lambda.chapter8;

/**
 * 	使用 Lambda 表达式实现模板方法模式
 * 	
 * 	使用 Lambda 表达式和方法引用，我们能换个角度思考模板方法模式，实现方式也跟以前不一样。
 *	
 *	模板方法模式真正要做的是将一组方法调用按一定顺序组织起来。
 *	如果用函数接口表示函数，用 Lambda 表达式或者方法引用实现这些接口，相比使用继承构建算法，就会得到极大的灵活性。
 *
 *	@author hzweiyongqiang
 */
public class Course14TemplateMethodPattern2 {
	/**
	 * 	我们也不需要强制 EmployeeLoanApplication 继承 PersonalLoanApplication 来达到复用，可以对同一个方法传递引用。
	 * 	它们之间是否天然存在继承关系取决于员工的借贷是否是普通人借贷这种特殊情况，或者是另外一种不同类型的借贷。
	 * 	因此，使用这种方式能让我们更加紧密地为问题建模。
	 */
	
	
	/**
	 *	异常抛出
	 */
	public class ApplicationDenied extends Exception{
		private static final long serialVersionUID = 1L;
		
	}
	
	/**
	 * 	如果申请失败，函数接口 Criteria 抛出异常
	 */
	public interface Criteria{
		public void check() throws ApplicationDenied;
	}

	/**
	 *	每一个属性都实现了函数接口 Criteria ，该接口检查一项标准，如果不达标就抛出一个问题域里的异常。
	 *	我们也可以选择从 check 方法返回一个类来表示成功或失败，但是沿用异常更加符合先前的实现
	 *
	 *	采用这种方式，而不是基于继承的模式的好处是不需要在 LoanApplication 及其子类中实现算法，分配功能时有了更大的灵活性。
	 */
	public class LoanApplication{
		private final Criteria identity;
		private final Criteria creditHistory;
		private final Criteria incomeHistory;
		
		public LoanApplication(Criteria identity,Criteria creditHistory,Criteria incomeHistory) {
			this.identity = identity;
			this.creditHistory = creditHistory;
			this.incomeHistory = incomeHistory;
		}
		
		public void checkLoanApplication() throws ApplicationDenied{
			identity.check();
			creditHistory.check();
			incomeHistory.check();
			reportFindings();
		}
		
		private void reportFindings() {
			
		}
	}
	
	/**
	 * 	Company 类中的检查方法
	 * 	将行为分配给 Company 类的原因是各个国家之间确认公司信息的方式不同。
	 */
	public class Company{
		public void checkIdentity() throws ApplicationDenied{
			
		}
		public void checkProfitAndLoss() throws ApplicationDenied{
			
		}
		public void checkHistoricalDebt() throws ApplicationDenied{
			
		}
	}
	
	/**
	 * 	CompanyLoanApplication 类声明了对应的检查方法
	 * 	使用函数接口实现检查方法并没有排除继承的方式。
	 * 	我们可以显式地在这些类中使用 Lambda 表达式或者方法引用。
	 */
	public class CompanyLoanApplication extends LoanApplication{
		public CompanyLoanApplication(Company company) {
			super(company::checkIdentity, company::checkHistoricalDebt, company::checkProfitAndLoss);
		}
	}
}
