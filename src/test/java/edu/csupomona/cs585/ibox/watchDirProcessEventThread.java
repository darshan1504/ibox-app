package edu.csupomona.cs585.ibox;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.lang.Thread.*;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;

public class watchDirProcessEventThread implements Runnable {

	private WatchDir objectwatchdir;

	public watchDirProcessEventThread(WatchDir watchdir) {
		this.objectwatchdir = watchdir;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			objectwatchdir.processEvents();

		}

	}

}
