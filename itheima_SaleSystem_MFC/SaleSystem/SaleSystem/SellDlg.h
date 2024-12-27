#pragma once
#include "afxwin.h"



// CSellDlg 窗体视图

class CSellDlg : public CFormView
{
	DECLARE_DYNCREATE(CSellDlg)

protected:
	CSellDlg();           // 动态创建所使用的受保护的构造函数
	virtual ~CSellDlg();

public:
	enum { IDD = DLALOG_SELL };
#ifdef _DEBUG
	virtual void AssertValid() const;
#ifndef _WIN32_WCE
	virtual void Dump(CDumpContext& dc) const;
#endif
#endif

protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

	DECLARE_MESSAGE_MAP()
	CComboBox m_combo;
private:
	int m_price;
	int m_num;
public:
	CString m_sellinfo;
	virtual void OnInitialUpdate();
	afx_msg void OnCbnSelchangeCombo1();
	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
};


