package nju.sec.yz.ExpressSystem.server;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate.UpdateSQLBuilder;

public class UpdateSQLTester {

	@Test
	public void test() {
		List<String> list=new ArrayList<>();
		list.add("po");
		list.add("id");
		assertEquals("update driver set po=?,set id=? where name=?",new UpdateSQLBuilder().getSQL("driver","name",list.iterator()));
	}

}
