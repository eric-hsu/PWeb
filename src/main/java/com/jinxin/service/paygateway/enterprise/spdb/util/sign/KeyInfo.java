package com.jinxin.service.paygateway.enterprise.spdb.util.sign;
import java.security.Key;
import java.util.Date;
public class KeyInfo {
	 private String _$2478;
	  private Date _$2705;
	  private Key _$2522;

	  public String getAlias()
	  {
	    return this._$2478;
	  }

	  public Date getCreationDate()
	  {
	    return this._$2705;
	  }

	  public Key getKey()
	  {
	    return this._$2522;
	  }

	  public void setAlias(String newAlias)
	  {
	    this._$2478 = newAlias;
	  }

	  public void setCreationDate(Date newCreationDate)
	  {
	    this._$2705 = newCreationDate;
	  }

	  public void setKey(Key newKey)
	  {
	    this._$2522 = newKey;
	  }
}
