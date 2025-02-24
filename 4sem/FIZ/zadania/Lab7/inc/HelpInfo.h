#ifndef __HELP_INFO_H
#define __HELP_INFO_H

#include <vector>
#include <String>
using namespace std;

typedef vector<string>helpTextList;
typedef helpTextList::iterator helpTextListIterator;


class HelpInfo{
public:
	static HelpInfo *getHelpInfoInstance();
	
	void addHelp(string str);
	void drawHelp();
private:
	HelpInfo();
	
	helpTextList helpList;
	static HelpInfo *pInstance;
	
};

#endif
