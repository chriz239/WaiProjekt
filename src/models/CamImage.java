package models;

import java.sql.Timestamp;
import java.util.UUID;

public class CamImage {
	private Long id;
	private Timestamp captureTime;
	private UUID uuid;
	private Long CamId;
	
	public String getThumbPath() {
		return "/WaiCams/bilder/Thumbs/" + CamId + "/" + captureTime.getMonth() + "/" + uuid.toString() + ".png";
	}
	public String getImagePath() {
		return "/WaiCams/bilder/" + CamId + "/" + captureTime.getMonth() + "/" + uuid.toString() + ".png";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getCaptureTime() {
		return captureTime;
	}
	public void setCaptureTime(Timestamp captureTime) {
		this.captureTime = captureTime;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public Long getCamId() {
		return CamId;
	}
	public void setCamId(Long camId) {
		CamId = camId;
	}
	
	
}
