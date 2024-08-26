package service.util;

import java.net.URL;

public class FixtureData {
    private String fixtureId;
    private String data;
    private String timeDeCasaNome;
    private URL timeDeCasaLogo;
    private String timeDeForaNome;
    private URL timeDeForaLogo;
    private String status;
    private boolean casaGanhou;
    private boolean foraGanhou;

    public FixtureData(String fixtureId, String data, String timeDeCasaNome, URL timeDeCasaLogo, String timeDeForaNome, URL timeDeForaLogo, String status, boolean casaGanhou, boolean foraGanhou) {
        this.fixtureId = fixtureId;
        this.data = data;
        this.timeDeCasaNome = timeDeCasaNome;
        this.timeDeCasaLogo = timeDeCasaLogo;
        this.timeDeForaNome = timeDeForaNome;
        this.timeDeForaLogo = timeDeForaLogo;
        this.status = status;
        this.casaGanhou = casaGanhou;
        this.foraGanhou = foraGanhou;
    }

	public String getFixtureId() {
		return fixtureId;
	}

	public void setFixtureId(String fixtureId) {
		this.fixtureId = fixtureId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTimeDeCasaNome() {
		return timeDeCasaNome;
	}

	public void setTimeDeCasaNome(String timeDeCasaNome) {
		this.timeDeCasaNome = timeDeCasaNome;
	}

	public URL getTimeDeCasaLogo() {
		return timeDeCasaLogo;
	}

	public void setTimeDeCasaLogo(URL timeDeCasaLogo) {
		this.timeDeCasaLogo = timeDeCasaLogo;
	}

	public String getTimeDeForaNome() {
		return timeDeForaNome;
	}

	public void setTimeDeForaNome(String timeDeForaNome) {
		this.timeDeForaNome = timeDeForaNome;
	}

	public URL getTimeDeForaLogo() {
		return timeDeForaLogo;
	}

	public void setTimeDeForaLogo(URL timeDeForaLogo) {
		this.timeDeForaLogo = timeDeForaLogo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isCasaGanhou() {
		return casaGanhou;
	}

	public void setCasaGanhou(boolean casaGanhou) {
		this.casaGanhou = casaGanhou;
	}

	public boolean isForaGanhou() {
		return foraGanhou;
	}

	public void setForaGanhou(boolean foraGanhou) {
		this.foraGanhou = foraGanhou;
	}
	
	public String toString() {
		return "FixtureData [fixtureId=" + fixtureId + ", data=" + data + ", timeDeCasaNome=" + timeDeCasaNome
				+ ", timeDeCasaLogo=" + timeDeCasaLogo + ", timeDeForaNome=" + timeDeForaNome + ", timeDeForaLogo="
				+ timeDeForaLogo + ", status=" + status + ", casaGanhou=" + casaGanhou + ", foraGanhou=" + foraGanhou
				+ "]";
	}
}

