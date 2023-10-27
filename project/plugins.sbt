resolvers += Resolver.mavenCentral
addSbtPlugin("com.github.sbt"      %% "sbt-native-packager" % "1.9.16")
addSbtPlugin("net.virtual-void"    % "sbt-dependency-graph" % "0.9.2")
addSbtPlugin("com.lightbend.sbt"   % "sbt-javaagent"        % "0.1.6")
addSbtPlugin("org.scoverage"       % "sbt-scoverage"        % "2.0.8")
addSbtPlugin("ch.epfl.scala"       % "sbt-scalafix"         % "0.11.1")
addSbtPlugin("org.scalameta"       % "sbt-scalafmt"         % "2.4.6")

ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
