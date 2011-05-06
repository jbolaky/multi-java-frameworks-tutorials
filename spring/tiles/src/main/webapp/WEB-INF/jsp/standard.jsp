<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>
<div
	style="border-style: solid; border-color: black; padding-top: 0.5cm; padding-bottom: 0.5cm; text-align: center;"><h1>Header</h1></div>
<div
	style="border-style: solid; border-color: black; padding-left: 2cm; padding-right: 2cm; padding-top: 2cm; padding-bottom: 5cm; float: left; width: 100px; text-align: center;">
<h1>Menu</h1></div>
<div
	style="border-style: solid; border-color: black; padding-left: 2cm; padding-top: 2cm; padding-bottom: 5cm; text-align: center;">
<h1><tiles:insertAttribute name="mainContent" /></h1></div>
<div
	style="clear: both; border-style: solid; border-color: black; padding-top: 0.5cm; padding-bottom: 0.5cm; text-align: center;"><h1>Footer</h1></div>
</body>
</html>