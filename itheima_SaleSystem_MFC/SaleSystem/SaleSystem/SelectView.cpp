// SelectView.cpp : 实现文件
//

#include "stdafx.h"
#include "SaleSystem.h"
#include "SelectView.h"
#include "resource.h"
#include "MainFrm.h"

// CSelectView

IMPLEMENT_DYNCREATE(CSelectView, CTreeView)

CSelectView::CSelectView()
{

}

CSelectView::~CSelectView()
{
}

BEGIN_MESSAGE_MAP(CSelectView, CTreeView)
	ON_NOTIFY_REFLECT(TVN_SELCHANGED, &CSelectView::OnTvnSelchanged)
END_MESSAGE_MAP()


// CSelectView 诊断

#ifdef _DEBUG
void CSelectView::AssertValid() const
{
	CTreeView::AssertValid();
}

#ifndef _WIN32_WCE
void CSelectView::Dump(CDumpContext& dc) const
{
	CTreeView::Dump(dc);
}
#endif
#endif //_DEBUG


// CSelectView 消息处理程序


void CSelectView::OnInitialUpdate()
{
	CTreeView::OnInitialUpdate();

	// TODO:  在此添加专用代码和/或调用基类

	// 完成初始化
	//图标资源的加载 CWinApp::LoadIcon
	//IDI_ICON_RE为图标资源ID
	HICON icon = AfxGetApp()->LoadIconW(IDI_ICON_RE);

	//图片列表的创建 CImageList::Create
	//30, 30：指定图标的宽度和高度
	//ILC_COLOR32：图标格式
	//1, 1：有多少图标就写多少
	m_imageList.Create(30, 30, ILC_COLOR32, 1, 1);

	//图片列表追加图标 CImageList::Add
	m_imageList.Add(icon);

	//获取数视图中的树控件 CTreeView::GetTreeCtrl
	m_treeCtrl = &GetTreeCtrl();

	//数控件设置图片列表 CTreeCtrl::SetImageList
	m_treeCtrl->SetImageList(&m_imageList, TVSIL_NORMAL);

	//树控件设置节点 CTreeCtrl::InsertItem
	m_treeCtrl->InsertItem(TEXT("个人信息"), 0, 0, NULL);
	m_treeCtrl->InsertItem(TEXT("销售管理"), 0, 0, NULL);
	m_treeCtrl->InsertItem(TEXT("库存信息"), 0, 0, NULL);
	m_treeCtrl->InsertItem(TEXT("库存添加"), 0, 0, NULL);
	m_treeCtrl->InsertItem(TEXT("库存删除"), 0, 0, NULL);
}


void CSelectView::OnTvnSelchanged(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMTREEVIEW pNMTreeView = reinterpret_cast<LPNMTREEVIEW>(pNMHDR);
	// TODO:  在此添加控件通知处理程序代码
	*pResult = 0;

	// 获取当前节点选择的项目
	HTREEITEM item = m_treeCtrl->GetSelectedItem();

	CString str = m_treeCtrl->GetItemText(item);

	MessageBox(str);

	if (str == _T("个人信息"))
	{
		//需要包含框架类头文件#include "MainFrm.h" 
		//CWnd::PostMessage 将一个消息放入窗口的消息队列
		//AfxGetMainWnd()：框架窗口对象的指针
		//AfxGetMainWnd()->GetSafeHwnd()：获取返回窗口的句柄，CWnd::GetSafeHwnd
		//NM_A：发送自定义消息
		//(WPARAM)NM_A：指定了附加的消息信息
		//(LPARAM)0：指定了附加的消息信息，此参数这里没有意义
		::PostMessage(AfxGetMainWnd()->GetSafeHwnd(), NM_A, (WPARAM)NM_A, (LPARAM)0);
	} 
	else if (str == _T("销售管理"))
	{
		::PostMessage(AfxGetMainWnd()->GetSafeHwnd(), NM_B, (WPARAM)NM_B, (LPARAM)0);
	}
	else if (str == _T("库存管理"))
	{
		::PostMessage(AfxGetMainWnd()->GetSafeHwnd(), NM_C, (WPARAM)NM_C, (LPARAM)0);
	}
	else if (str == _T("库存增加"))
	{
		::PostMessage(AfxGetMainWnd()->GetSafeHwnd(), NM_D, (WPARAM)NM_D, (LPARAM)0);
	}
	else if (str == _T("库存删除"))
	{
		::PostMessage(AfxGetMainWnd()->GetSafeHwnd(), NM_E, (WPARAM)NM_E, (LPARAM)0);
	}
}
