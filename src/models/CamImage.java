package models;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.UUID;

public class CamImage {
	private Long id;
	private Timestamp captureTime;
	private UUID uuid;
	private BufferedImage thumbnail;
	private Long CamId;
	
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
	public String getFullImagePath() {
		return CamId.toString() + captureTime.getMonth();
	}
	public void setFullImage(BufferedImage fullImage) {
		this.fullImage = fullImage;
	}
	public BufferedImage getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(BufferedImage thumbnail) {
		this.thumbnail = thumbnail;
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
