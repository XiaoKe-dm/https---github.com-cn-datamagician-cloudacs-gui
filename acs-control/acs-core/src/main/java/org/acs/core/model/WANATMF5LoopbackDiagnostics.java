package org.acs.core.model;

public class WANATMF5LoopbackDiagnostics {

	private VersionObject DiagnosticsState;
	private VersionObject NumberOfRepetitions;
	private VersionObject Timeout;
	private VersionObject SuccessCount;
	private VersionObject FailureCount;
	private VersionObject AverageResponseTime;
	private VersionObject MinimumResponseTime;
	private VersionObject MaximumResponseTime;
	public VersionObject getDiagnosticsState() {
		return DiagnosticsState;
	}
	public void setDiagnosticsState(VersionObject diagnosticsState) {
		DiagnosticsState = diagnosticsState;
	}
	public VersionObject getNumberOfRepetitions() {
		return NumberOfRepetitions;
	}
	public void setNumberOfRepetitions(VersionObject numberOfRepetitions) {
		NumberOfRepetitions = numberOfRepetitions;
	}
	public VersionObject getTimeout() {
		return Timeout;
	}
	public void setTimeout(VersionObject timeout) {
		Timeout = timeout;
	}
	public VersionObject getSuccessCount() {
		return SuccessCount;
	}
	public void setSuccessCount(VersionObject successCount) {
		SuccessCount = successCount;
	}
	public VersionObject getFailureCount() {
		return FailureCount;
	}
	public void setFailureCount(VersionObject failureCount) {
		FailureCount = failureCount;
	}
	public VersionObject getAverageResponseTime() {
		return AverageResponseTime;
	}
	public void setAverageResponseTime(VersionObject averageResponseTime) {
		AverageResponseTime = averageResponseTime;
	}
	public VersionObject getMinimumResponseTime() {
		return MinimumResponseTime;
	}
	public void setMinimumResponseTime(VersionObject minimumResponseTime) {
		MinimumResponseTime = minimumResponseTime;
	}
	public VersionObject getMaximumResponseTime() {
		return MaximumResponseTime;
	}
	public void setMaximumResponseTime(VersionObject maximumResponseTime) {
		MaximumResponseTime = maximumResponseTime;
	}
	
	
}
