package com.cloudwalk.test.client;

import com.cloudwalk.test.core.App;

public class ClientFactory {

	private static Client client = (Client) InterfaceAntoImpl.autoHttpImpl(App.SERVER, Client.class);

	public static Client getClient() {
		return client;
	}

	public static interface Client extends IBankOcr, IFace, IIDCardOcr, ILipreading, ILiveness {

	}
}
