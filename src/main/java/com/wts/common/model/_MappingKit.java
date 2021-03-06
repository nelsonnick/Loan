package com.wts.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("bank", "id", Bank.class);
		arp.addMapping("change", "id", Change.class);
		arp.addMapping("department", "id", Department.class);
		arp.addMapping("loan", "id", Loan.class);
		arp.addMapping("type", "id", Type.class);
		arp.addMapping("user", "id", User.class);
	}
}

