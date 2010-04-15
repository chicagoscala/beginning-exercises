import sbt._

class ScalaExercisesProject(info: ProjectInfo) extends DefaultProject(info)
{
  override def repositories = Set(
    "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots")
      
  val scalatest  = "org.scalatest" % "scalatest" % "1.0"
  // val scalatest  = "org.scalatest" % "scalatest" % "1.1-SNAPSHOT"
  // val scalatest  = "org.scalatest" % "scalatest" % "1.0.1-for-scala-2.8.0.Beta1-RC1-SNAPSHOT"
  
  override def compileOptions = super.compileOptions ++ 
    Seq("-deprecation", "-unchecked").map(x => CompileOption(x))

  lazy val about = task { 
    println("Chicago-Area Scala Enthusiasts: Hands-on Exercises")
    None 
  }
}