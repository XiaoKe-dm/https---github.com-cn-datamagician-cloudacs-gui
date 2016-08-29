package org.acs.core.model;

public class Queue {

	private VersionObject QueueKey;
	private VersionObject QueueEnable;
	private VersionObject QueueStatus;
	private VersionObject QueueInterface;
	private VersionObject QueueWeight;
	private VersionObject QueuePrecedence;
	private VersionObject SchedulerAlgorithm;
	private VersionObject ShapingRate;
	private VersionObject ShapingBurstSize;
	public VersionObject getQueueKey() {
		return QueueKey;
	}
	public void setQueueKey(VersionObject queueKey) {
		QueueKey = queueKey;
	}
	public VersionObject getQueueEnable() {
		return QueueEnable;
	}
	public void setQueueEnable(VersionObject queueEnable) {
		QueueEnable = queueEnable;
	}
	public VersionObject getQueueStatus() {
		return QueueStatus;
	}
	public void setQueueStatus(VersionObject queueStatus) {
		QueueStatus = queueStatus;
	}
	public VersionObject getQueueInterface() {
		return QueueInterface;
	}
	public void setQueueInterface(VersionObject queueInterface) {
		QueueInterface = queueInterface;
	}
	public VersionObject getQueueWeight() {
		return QueueWeight;
	}
	public void setQueueWeight(VersionObject queueWeight) {
		QueueWeight = queueWeight;
	}
	public VersionObject getQueuePrecedence() {
		return QueuePrecedence;
	}
	public void setQueuePrecedence(VersionObject queuePrecedence) {
		QueuePrecedence = queuePrecedence;
	}
	public VersionObject getSchedulerAlgorithm() {
		return SchedulerAlgorithm;
	}
	public void setSchedulerAlgorithm(VersionObject schedulerAlgorithm) {
		SchedulerAlgorithm = schedulerAlgorithm;
	}
	public VersionObject getShapingRate() {
		return ShapingRate;
	}
	public void setShapingRate(VersionObject shapingRate) {
		ShapingRate = shapingRate;
	}
	public VersionObject getShapingBurstSize() {
		return ShapingBurstSize;
	}
	public void setShapingBurstSize(VersionObject shapingBurstSize) {
		ShapingBurstSize = shapingBurstSize;
	}
	
	
}
