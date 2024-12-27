// UserDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "SaleSystem.h"
#include "UserDlg.h"
#include "InfoFile.h"

// CUserDlg

IMPLEMENT_DYNCREATE(CUserDlg, CFormView)

CUserDlg::CUserDlg()
	: CFormView(CUserDlg::IDD)
	, m_user(_T(""))
	, m_newPwd(_T(""))
	, m_surePwd(_T(""))
	, m_name(_T(""))
{

}

CUserDlg::~CUserDlg()
{
}

void CUserDlg::DoDataExchange(CDataExchange* pDX)
{
	CFormView::DoDataExchange(pDX);
	DDX_Text(pDX, IDC_EDIT1, m_user);
	DDX_Text(pDX, IDC_EDIT3, m_newPwd);
	DDX_Text(pDX, IDC_EDIT4, m_surePwd);
	DDX_Text(pDX, IDC_EDIT2, m_name);
}

BEGIN_MESSAGE_MAP(CUserDlg, CFormView)
	ON_BN_CLICKED(IDC_BUTTON1, &CUserDlg::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &CUserDlg::OnBnClickedButton2)
END_MESSAGE_MAP()


// CUserDlg 诊断

#ifdef _DEBUG
void CUserDlg::AssertValid() const
{
	CFormView::AssertValid();
}

#ifndef _WIN32_WCE
void CUserDlg::Dump(CDumpContext& dc) const
{
	CFormView::Dump(dc);
}
#endif
#endif //_DEBUG


// CUserDlg 消息处理程序


// 初始化操作
void CUserDlg::OnInitialUpdate()
{
	CFormView::OnInitialUpdate();

	// TODO:  在此添加专用代码和/或调用基类
	CInfoFile file;		// 需要头文件 "InfoFile.h"
	CString name, pwd;
	file.ReadLogin(name, pwd); // 读取文件的用户名和密码

	// init persone info
	m_user = _T("销售员");
	m_name = name;

	UpdateData(FALSE); // 把数据更新到控件上
}


void CUserDlg::OnBnClickedButton1()
{
	// TODO:  在此添加控件通知处理程序代码

	UpdateData(TRUE); // 更新控件内容到对应的变量中

	// 非空
	if (m_newPwd.IsEmpty() || m_surePwd.IsEmpty())
	{
		MessageBox(_T("输入密码不可为空"));
		return;
	}

	if (m_newPwd != m_surePwd)
	{
		MessageBox(_T("两次输入密码不匹配"));
		return;
	}

	CInfoFile file;
	CString name, pwd;
	file.ReadLogin(name, pwd);

	if (m_surePwd == pwd)
	{
		MessageBox(_T("输入密码和旧密码相等"));
		return;
	}

	//把用户名和密码的CString类型转为char *
	// 因为 WritePwd 是 char * 类型
	char *tmpName, *tmpPwd;
	CStringA tmp1;
	tmp1 = name;
	tmpName = tmp1.GetBuffer();

	CStringA tmp2;
	tmp2 = m_surePwd;
	tmpPwd = tmp2.GetBuffer();

	file.WritePwd(tmpName, tmpPwd);

	MessageBox(_T("密码修改成功"));

	//输入框内容清空
	m_surePwd.Empty();
	m_newPwd.Empty();
	UpdateData(FALSE); //把数据更新到控件上
}


void CUserDlg::OnBnClickedButton2()
{
	// TODO:  在此添加控件通知处理程序代码
	//输入框内容清空
	m_surePwd.Empty();
	m_newPwd.Empty();
	UpdateData(FALSE); //把数据更新到控件上
}
