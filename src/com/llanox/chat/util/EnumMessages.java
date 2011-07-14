package com.llanox.chat.util;

public enum EnumMessages {
	
	YOU_CAN_GETTING_START("1","Ahora puede chatear!!"),
	MAX_NUM_PATIENTS_CHATTING("2","Se ha superado el máximo de usuarios a atender");
	
	
	
	private String code;
	private String name;

	EnumMessages(String code, String name) {
		this.code = code;
		this.name = name;
		
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static EnumMessages getValue(String code) {

            if (code == null) {
                    return null;
            }

            for (EnumMessages type : values()) {

                if (code.equals(type.getCode())) {
                        return type;
                }
            }

            return null;
	}
	
	public String toString(){
		return name;
	}
	

}
