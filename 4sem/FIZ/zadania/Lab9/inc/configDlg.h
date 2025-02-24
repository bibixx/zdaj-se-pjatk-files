#ifndef __CONFIG_DLG_H
#define __CONFIG_DLG_H
#include <windows.h>

typedef struct ConfigParam{
	int fullScreen;
};

class ConfigDlg{
public:
	
	static int Execute();
	static int getFullScreen() {return fullScreen;}
	static BOOL CALLBACK DlgProc(HWND hwnd, UINT Message, WPARAM wParam, LPARAM lParam);
	static ConfigParam getParams();
private:
	ConfigDlg();
	static int fullScreen;
};

#endif
