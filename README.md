# mvn-dependency-tree-flat-parser
An mvn dependency tree parser that flatten all dependencies
## How to get dependency txt file that support muti-module projects
Here pwd is the trick.
```shell
mvn dependency:tree -DoutputFile=`pwd`/mvn_dependency_tree.txt -DoutputType=text -DappendOutput=true
```
## How to use mvndeptree-flatparser
```
// str mode
FlatParseResult res = FlatParser.parse(str);

// File mode
FlatParseResult res = FlatParser.parse(file);

// Get result
for (FlatParseItem item : res.getItems()) {
    System.out.println(item.getGroup());
    System.out.println(item.getArtifactId());
    System.out.println(item.getVersion());
    System.out.println(item.getScope());
    System.out.println(item.getType());
}
```
