package introsde.document.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import introsde.document.dao.LifeCoachDao;

/**
 * The persistent class for the "Goal" database table.
 * 
 */
@Entity
@Table(name="\"Goal\"")
@NamedQueries({@NamedQuery(name="Goal.findAll", query="SELECT g FROM Goal g"),
@NamedQuery(name = "Goal.findByPersonId", query = "SELECT m FROM Goal m WHERE m.idPerson = ?1"),})

public class Goal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="sqlite_goal")
	@TableGenerator(name="sqlite_goal", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="\"Goal\"")

	@Column(name="\"idGoal\"")
	private int idGoal;

	@Column(name="\"ActualWeight\"")
	private double actualWeight;

	@Column(name="\"FinalWeight\"")
	private double finalWeight;

	@Column(name="\"HeartRate\"")
	private int heartRate;

	@Column(name="\"Height\"")
	private double height;

	@Column(name="\"InitialWeight\"")
	private double initialWeight;

	@Column(name="\"LostWeight\"")
	private int lostWeight;

	@Column(name="\"MaxBloodPressure\"")
	private int maxBloodPressure;

	@Column(name="\"MinBloodPressure\"")
	private int minBloodPressure;

	@Column(name="\"SleepHours\"")
	private int sleepHours;
	@Column(name="\"idPerson\"")
	private int idPerson;
	@Column(name="\"Steps\"")
	private int steps;
	
	
	public Goal() {
	}

	
	public Goal(double actualWeight, double height, double finalWeight, int lostWeight, int maxBloodPressure, int minBloodPressure, int idPerson, int steps){
		this.actualWeight = actualWeight;
		this.height = height;
		this.finalWeight=finalWeight;
		this.height=height;
		this.maxBloodPressure=maxBloodPressure;
		this.minBloodPressure=minBloodPressure;
		this.lostWeight=lostWeight;
		this.idPerson = idPerson;
		this.steps=steps;
	}

	public double getActualWeight() {
		return this.actualWeight;
	}

	public void setActualWeight(double actualWeight) {
		this.actualWeight = actualWeight;
	}

	public double getFinalWeight() {
		return this.finalWeight;
	}

	public void setFinalWeight(double finalWeight) {
		this.finalWeight = finalWeight;
	}

	public int getHeartRate() {
		return this.heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getIdGoal() {
		return this.idGoal;
	}

	public void setIdGoal(int idGoal) {
		this.idGoal = idGoal;
	}
	
	public int getSteps() {
		return this.steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	// we make this transient for JAXB to avoid and infinite loop on serialization
	@XmlTransient
	public int getIdPerson() {
		return idPerson;
	}

	public void setPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public double getInitialWeight() {
		return this.initialWeight;
	}

	public void setInitialWeight(double initialWeight) {
		this.initialWeight = initialWeight;
	}

	public int getLostWeight() {
		return this.lostWeight;
	}

	public void setLostWeight(int lostWeight) {
		this.lostWeight = lostWeight;
	}

	public int getMaxBloodPressure() {
		return this.maxBloodPressure;
	}

	public void setMaxBloodPressure(int maxBloodPressure) {
		this.maxBloodPressure = maxBloodPressure;
	}

	public int getMinBloodPressure() {
		return this.minBloodPressure;
	}

	public void setMinBloodPressure(int minBloodPressure) {
		this.minBloodPressure = minBloodPressure;
	}

	public int getSleepHours() {
		return this.sleepHours;
	}

	public void setSleepHours(int sleepHours) {
		this.sleepHours = sleepHours;
	}

	public String toString() {
		return "Goal ( " + idGoal + ", " + height + ", "
				+ initialWeight + ", " +idPerson	+  height +  ")";
	}
	
	// database operations
		public static Goal getGoalById(int goalId) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			Goal m = em.find(Goal.class, goalId);
			LifeCoachDao.instance.closeConnections(em);
			return m;
		}
		public static Goal getGoalByIdPerson(int idPerson) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			Goal m = em.find(Goal.class, idPerson);
			LifeCoachDao.instance.closeConnections(em);
			return m;
		}

		public static List<Goal> getAll() {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			List<Goal> list = em.createNamedQuery("Goal.findAll",
					Goal.class).getResultList();
			LifeCoachDao.instance.closeConnections(em);
			return list;
		}

		public static Goal saveGoal(Goal m) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(m);
			tx.commit();
			LifeCoachDao.instance.closeConnections(em);
			return m;
		}

		public static Goal updateGoal(Goal m) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			m = em.merge(m);
			tx.commit();
			LifeCoachDao.instance.closeConnections(em);
			return m;
		}

		public static void removeGoal(Goal m) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			m = em.merge(m);
			em.remove(m);
			tx.commit();
			LifeCoachDao.instance.closeConnections(em);
		}
		/**
		 * Returns the list of the goal associated of one person
		 * @param p
		 * @return
		 */
		public static Goal getAllGoals(int idPerson) {
			EntityManager em = LifeCoachDao.instance.createEntityManager();
			Goal goalsList = em.createNamedQuery("Goal.findByPersonId", Goal.class)
					.setParameter(1, idPerson).getSingleResult();
			LifeCoachDao.instance.closeConnections(em);
			return goalsList;
		}
}
