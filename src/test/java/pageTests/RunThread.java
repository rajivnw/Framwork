package pageTests;

import testbase.TestBaseClass;

public class RunThread implements Runnable {

	public static void main(String[] args) {

		RunThread runthread = new RunThread();
		Thread thread1 = new Thread(runthread, "First Thread");
		thread1.start();

		Thread thread2 = new Thread(runthread, "Second Thread");
		thread2.start();

	}

	public void run() {
		System.out.println("Thread name " + Thread.currentThread().getName());
		TestBaseClass testBase = new TestBaseClass();
		testBase.onStart1();
		testBase.openWebsite();

		TestMethod testMethod = new TestMethod();
		testMethod.SearchCompany("AXS");
		testBase.onFinish1();

	}

}
