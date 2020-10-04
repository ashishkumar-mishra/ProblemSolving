package com.algo.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given an array of jobs where every job has a deadline and associated profit
 * if the job is finished before the deadline. It is also given that every job
 * takes single unit of time, so the minimum possible deadline for any job is
 * one. How to maximize total profit if only one job can be scheduled at a time.
 *
 */
public class JobSequence {

	public char[] getjobSequence(List<Job> jobs) {

		// sorting the jobs in descending order of profit

		Collections.sort(jobs, (j1, j2) -> j2.profit - j1.profit);
		// finding max deadline
		int maxDeadline = 0;
		for (Job job : jobs) {
			if (job.getDeadline() > maxDeadline) {
				maxDeadline = job.getDeadline();
			}
		}
		char[] list = new char[maxDeadline];
		boolean[] slots = new boolean[maxDeadline];

		for (Job job : jobs) {
			for (int j = job.getDeadline() - 1; j >= 0; j--) {
				if (slots[j] == false) {
					list[j] = job.getJobId();
					slots[j] = true;
					break;
				}
			}
		}
		return list;
	}

	class Job {
		private char jobId;
		private int deadline;
		private int profit;

		Job(char jobId, int deadline, int profit) {
			this.jobId = jobId;
			this.deadline = deadline;
			this.profit = profit;
		}

		public char getJobId() {
			return jobId;
		}

		public int getDeadline() {
			return deadline;
		}

		public int getProfit() {
			return profit;
		}
	}

	public static void main(String[] args) {
		JobSequence jobSequence = new JobSequence();
		List<JobSequence.Job> jobs = new ArrayList<>();
		jobs.add(jobSequence.new Job('a', 2, 100));
		jobs.add(jobSequence.new Job('b', 1, 19));
		jobs.add(jobSequence.new Job('c', 2, 27));
		jobs.add(jobSequence.new Job('d', 1, 25));
		jobs.add(jobSequence.new Job('e', 3, 15));
		System.out.println(jobSequence.getjobSequence(jobs));
	}
}
