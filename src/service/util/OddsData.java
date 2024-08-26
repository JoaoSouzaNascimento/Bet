package service.util;

public class OddsData {
	private float timeDeCasaGanhaOdd;
	private float timeDeForaGanhaOdd;
	private float empateOdd;

	public OddsData(float timeDeCasaGanhaOdd, float timeDeForaGanhaOdd, float empateOdd) {
		this.timeDeCasaGanhaOdd = timeDeCasaGanhaOdd;
		this.timeDeForaGanhaOdd = timeDeForaGanhaOdd;
		this.empateOdd = empateOdd;
	}

	public float getTimeDeCasaGanhaOdd() {
		return timeDeCasaGanhaOdd;
	}

	public void setTimeDeCasaGanhaOdd(float timeDeCasaGanhaOdd) {
		this.timeDeCasaGanhaOdd = timeDeCasaGanhaOdd;
	}

	public float getTimeDeForaGanhaOdd() {
		return timeDeForaGanhaOdd;
	}

	public void setTimeDeForaGanhaOdd(float timeDeForaGanhaOdd) {
		this.timeDeForaGanhaOdd = timeDeForaGanhaOdd;
	}

	public float getEmpateOdd() {
		return empateOdd;
	}

	public void setEmpateOdd(float empateOdd) {
		this.empateOdd = empateOdd;
	}

}
