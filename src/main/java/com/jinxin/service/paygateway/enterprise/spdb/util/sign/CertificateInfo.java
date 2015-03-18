package com.jinxin.service.paygateway.enterprise.spdb.util.sign;
import java.security.cert.Certificate;
import java.util.Date;
public class CertificateInfo {
	 private String _$2478;
	  private Date _$2705;
	  private Certificate _$2714;
	  private Certificate[] _$2715;

	  String getAlias()
	  {
	    return this._$2478;
	  }

	  Certificate getCertificate()
	  {
	    return this._$2714;
	  }

	  Certificate[] getCertificateChain()
	  {
	    return this._$2715;
	  }

	  Date getCreationDate()
	  {
	    return this._$2705;
	  }

	  public void setAlias(String newAlias)
	  {
	    this._$2478 = newAlias;
	  }

	  public void setCertificate(Certificate newCertificate)
	  {
	    this._$2714 = newCertificate;
	  }

	  public void setCertificateChain(Certificate[] newCertificateChain)
	  {
	    this._$2715 = newCertificateChain;
	  }

	  public void setCreationDate(Date newCreationDate)
	  {
	    this._$2705 = newCreationDate;
	  }
}
