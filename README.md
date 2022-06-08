# mvn-dependency-tree-flat-parser
An mvn dependency tree parser that flatten all dependencies.
Flatten means won't record the dependency parent / child relation, just because I don't need this information.

## How to get dependency txt file that support muti-module projects
Here pwd is the trick.
```shell
mvn dependency:tree -DoutputFile=`pwd`/mvn_dependency_tree.txt -DoutputType=text -DappendOutput=true
```
## How to get using maven
```
<!-- https://mvnrepository.com/artifact/com.coder4/mvndeptree-flatparser -->
<dependency>
    <groupId>com.coder4</groupId>
    <artifactId>mvndeptree-flatparser</artifactId>
    <version>0.0.1</version>
</dependency>

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
