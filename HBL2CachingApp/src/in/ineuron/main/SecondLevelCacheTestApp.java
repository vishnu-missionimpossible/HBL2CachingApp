package in.ineuron.main;

import org.hibernate.Session;

import in.ineuron.Model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class SecondLevelCacheTestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = null;
		InsurancePolicy policy = null;
		
		session = HibernateUtil.getSession();
		
			try {
				policy = session.get(InsurancePolicy.class, 1L);//Get from DB and keep it in L2 and L1 cache
				System.out.println("1 :: "+policy);
				
				System.in.read();
				policy = session.get(InsurancePolicy.class, 1L);//Get from L1 cache
				System.out.println("2 :: "+policy);
				
				System.in.read();
				session.evict(policy);
				policy = session.get(InsurancePolicy.class, 1L);//Get from L2 cache
				System.out.println("3 :: "+policy);
				
				session.clear();//clear the data in the L1 Cache
				
				Thread.sleep(20000);
				
				policy = session.get(InsurancePolicy.class, 1L);//Get from DB and keep it in L2 and L1 cache
				System.out.println("4 :: "+policy);
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}finally {
				
				HibernateUtil.closeSession(session);
			}

	}

}
