package models;

import java.sql.Timestamp;
import java.util.UUID;

public class CamImage {
	private Long id;
	private Timestamp captureTime;
	private UUID uuid;
	private Long CamId;
	private String imagePath;
	private String thumbPath;
	
	public String getThumbPath() {
		return thumbPath;
	}
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String path) {
		this.imagePath = path;
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
