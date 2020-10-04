package com.algo.disjointset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of jobs where each job j has a deadline >=1 and profit >=0. Only
 * one job can be scheduled at a time. Each job takes one unit of time to
 * complete. We earn the profit if and only if the job is completed by its
 * deadline. Implementation to find the subset of jobs that maximizes profit.
 *
 */

public class JobSequence {

	int[] parents;

	public List<Character> getJobSequence(final List<Job> jobs) {
		Collections.sort(jobs, (j1, j2) -> j2.profit - j1.profit);
		int maxDeadLine = getMaxDeadline(jobs);
		parents = new int[maxDeadLine + 1];
		Character[] result = new Character[maxDeadLine];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		for (Job job : jobs) {
			int slot = find(job.deadLine);
			if (slot > 0) {
				result[slot - 1] = job.jobID;
				union(slot - 1, slot);
			}
		}
		return Arrays.asList(result);
	}

	private int find(int deadLine) {
		if (parents[deadLine] == deadLine) {
			return deadLine;
		} else {
			return parents[deadLine] = find(parents[deadLine]);
		}
	}

	private void union(int deadLine1, int deadLine2) {
		parents[deadLine2] = deadLine1;
	}

	private int getMaxDeadline(List<Job> jobs) {
		int maxDeadLine = Integer.MIN_VALUE;
		for (Job job : jobs) {
			if (job.deadLine > maxDeadLine) {
				maxDeadLine = job.deadLine;
			}
		}
		return maxDeadLine;
	}

	public static void main(String[] args) {
		JobSequence jobSequence = new JobSequence();
		List<Job> jobs = new ArrayList<>();
		jobs.add(new Job('a', 2, 100));
		jobs.add(new Job('b', 1, 19));
		jobs.add(new Job('c', 2, 27));
		jobs.add(new Job('d', 1, 25));
		jobs.add(new Job('e', 3, 15));
		System.out.println(jobSequence.getJobSequence(jobs));
	}
}

class Job {
	char jobID;
	int deadLine;
	int profit;

	Job(char jobID, int deadLine, int profit) {
		this.jobID = jobID;
		this.deadLine = deadLine;
		this.profit = profit;
	}
}
