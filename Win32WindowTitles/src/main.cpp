#define UNICODE
#define _UNICODE

#include <windows.h>
#include <iostream>
#include <vector>
#include <string>

BOOL CALLBACK EnumWindowsProc(HWND hwnd, LPARAM lParam) {
    auto* windowTitles = reinterpret_cast<std::vector<std::wstring>*>(lParam);
    
    if (IsWindowVisible(hwnd)) {
        int length = GetWindowTextLengthW(hwnd);
        if (length > 0) {
            std::wstring buffer;
            buffer.resize(length + 1);
            GetWindowTextW(hwnd, &buffer[0], length + 1);
            
            std::wstring title(buffer.c_str());
            if (!title.empty()) {
                windowTitles->push_back(title);
            }
        }
    }
    return TRUE;
}

int main() {
    std::vector<std::wstring> windowTitles;
    
    EnumWindows(EnumWindowsProc, reinterpret_cast<LPARAM>(&windowTitles));
    
    std::wcout << L"Processes running in the computer:\n";
    for (const auto& title : windowTitles) {
        std::wcout << L"    " << title << L"\n";
    }
    
    return 0;
}