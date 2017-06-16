package com.test;

import java.io.IOException;

import com.jsan.util.upload.SequenceCodeNamingAdapter;

public class TempTest {

	public static void main(String[] args) throws IOException {

		SequenceCodeNamingAdapter adapter = new SequenceCodeNamingAdapter(16);

		for (int i = 0; i < 1000; i++) {
			System.out.println(adapter.getName(null, null, 0));
		}

	}
}
